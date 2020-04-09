import com.sun.jmx.mbeanserver.Introspector;

public class HolaMundoHilos implements Runnable {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new HolaMundoHilos());
        Thread hilo2 = new Thread(new HolaMundoHilos());

        /*
        MuestraNombreConRunnable ejecutable1 = new MuestraNombreConRunnable();
        MuestraNombreConRunnable ejecutable2 = new MustraNombreConnRunnable();
        Thread hilo1 = new Thread(ejecutable1);
        Thread hilo2 = new Thread(ejecutable2);
        */
        hilo1.start();
        hilo1.setName("Hola");
        hilo2.start();
        hilo2.setName("Mundo");
    }

    public void run() {
        int pausa;

        while (true) {
            try {                
                // Se usa el método estático currentThread para obtener una 
                // referencia al hilo actual y después llamar al método getName
                System.out.println(Thread.currentThread().getName());
                pausa = (int)(Math.random() * 3000);
                Thread.sleep(pausa);
            } catch (InterruptedException inEx) {
                System.out.println(inEx);
            }
        }
    }
}