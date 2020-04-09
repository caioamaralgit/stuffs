public class HiloMuestraNombre extends Thread {
    public static void main (String[] args) {
        HiloMuestraNombre hilo1, hilo2;
        hilo1 = new HiloMuestraNombre();
        hilo2 = new HiloMuestraNombre();

        hilo1.start();
        hilo2.start();
    }

    public void run() {
        int pausa;

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(getName() + " está siendo ejecutado.");
                pausa = (int)(Math.random() * 3000);
                sleep(pausa); //0-3 segundos.
            } catch (InterruptedException inEx) {
                System.out.println(inEx);
            }
        }
    }
}