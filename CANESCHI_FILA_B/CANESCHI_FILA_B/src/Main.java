public class Main {
    public static void main(String[] args) {
        Botte bottiglia = new Botte(100);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Bevitore(i + 1, bottiglia));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La botte è vuota. Nessun altro vino rimasto.");
    }
}