import java.util.Random;

public class Botte {
    private int vinoRimanente;

    public Botte(int capacita) {
        this.vinoRimanente = capacita;
    }

    public synchronized int bere(int bevitori) {
        int bevuta = Math.min(3, vinoRimanente);
        if (bevuta > 0) {
            Random random = new Random();
            int quantitaBevuta = random.nextInt(bevuta) + 1;
            vinoRimanente -= quantitaBevuta;
            System.out.println("-------------------------------------------------------------");
            System.out.println("| Bevitore " + bevitori + " ha bevuto " + quantitaBevuta + " litri di vino. Rimasti: " + vinoRimanente + " litri. |"); 
            System.out.println("-------------------------------------------------------------");
        }
        return bevuta;
    }

    public synchronized boolean isVinoRimanente() {
        return vinoRimanente > 0;
    }
}