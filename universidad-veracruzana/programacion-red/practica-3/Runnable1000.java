import com.sun.jmx.mbeanserver.Introspector;

public class Runnable1000 implements Runnable {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Runnable1000());
        Thread hilo2 = new Thread(new Runnable1000());

        /*
        MuestraNombreConRunnable ejecutable1 = new MuestraNombreConRunnable();
        MuestraNombreConRunnable ejecutable2 = new MustraNombreConnRunnable();
        Thread hilo1 = new Thread(ejecutable1);
        Thread hilo2 = new Thread(ejecutable2);
        */
        hilo1.start();
        hilo2.start();
    }

    public void run() {
        int pausa;

        for (int i = 1; i <= 1000; i++) {
            try {
                // Se usa el método estático currentThread para obtener una 
                // referencia al hilo actual y después llamar al método getName
                if (Thread.currentThread().getName().equals("Thread-0"))
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                else
                    System.out.println(Thread.currentThread().getName() + ": " + (1001 - i)); 
                pausa = (int)(Math.random() * 3000);
                Thread.sleep(pausa);
            } catch (InterruptedException inEx) {
                System.out.println(inEx);
            }
        }
    }
}