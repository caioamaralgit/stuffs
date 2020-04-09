import com.sun.jmx.mbeanserver.Introspector;

public class NombrarThreads implements Runnable {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new NombrarThreads());
        Thread hilo2 = new Thread(new NombrarThreads());

        /*
        MuestraNombreConRunnable ejecutable1 = new MuestraNombreConRunnable();
        MuestraNombreConRunnable ejecutable2 = new MustraNombreConnRunnable();
        Thread hilo1 = new Thread(ejecutable1);
        Thread hilo2 = new Thread(ejecutable2);
        */
        hilo1.start();
        hilo1.setName("Nameless");
        hilo2.start();
        hilo2.setName("Thread-1");
    }

    public void run() {
        int pausa;

        for (int i = 0; i < 10; i++) {
            try {
                if (i == 3) {
                    if (Thread.currentThread().getName().equals("Thread-1")) {
                        System.out.println(Thread.currentThread().getName() + " ahora se llama Caio.");
                        Thread.currentThread().setName("Caio");
                    } else {
                        System.out.println(Thread.currentThread().getName() + " ahora se llama Marcos.");
                        Thread.currentThread().setName("Marcos");
                    }
                }


                // Se usa el método estático currentThread para obtener una 
                // referencia al hilo actual y después llamar al método getName
                System.out.println(Thread.currentThread().getName() + " está siendo ejecutado.");
                pausa = (int)(Math.random() * 3000);
                Thread.sleep(pausa);
            } catch (InterruptedException inEx) {
                System.out.println(inEx);
            }
        }
    }
}