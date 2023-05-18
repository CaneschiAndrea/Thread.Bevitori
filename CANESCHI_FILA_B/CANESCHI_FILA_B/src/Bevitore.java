import java.util.Random;

public class Bevitore extends Thread{
    private static final int TEMPO_MINIMO_ATTESA = 1000; // 1 secondo di attesa
    private static final int TEMPO_MAXIMO_ATTESA = 5000; // 5 secondi di attesa
    private static final int TEMPO_MINIMO_BEVUTA = 2000; // 2 secondi di attesa
    private static final int TEMPO_MAXIMO_BEVUTA = 3000; // 3 secondi di attesa

    private final int id;
    private final Botte bottiglia;
    private final Random random;

    public Bevitore(int id, Botte bottiglia) {
        this.id = id;
        this.bottiglia = bottiglia;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (bottiglia.isVinoRimanente()) {
            try {
                Thread.sleep(getTempoAttesa());
                synchronized (bottiglia) {
                    while (bottiglia.bere(id) == 0) {
                        if (!bottiglia.isVinoRimanente()) {
                            return;
                        }
                        bottiglia.wait();
                    }
                    Thread.sleep(getTempoBevuta());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getTempoAttesa() {
        return random.nextInt(TEMPO_MAXIMO_ATTESA - TEMPO_MINIMO_ATTESA + 1) + TEMPO_MINIMO_ATTESA;
    }

    private int getTempoBevuta() {
        return random.nextInt(TEMPO_MAXIMO_BEVUTA - TEMPO_MINIMO_BEVUTA + 1) + TEMPO_MINIMO_BEVUTA;
    }
}