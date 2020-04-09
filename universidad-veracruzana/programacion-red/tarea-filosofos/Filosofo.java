import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;

class Filosofo extends Thread {
    private static int pausa = 3000;
    private Tenedor tenedorIzquierdo;
    private Tenedor tenedorDerecho;

    public Filosofo (Tenedor tenIzq, Tenedor tenDer) {
        this.tenedorIzquierdo = tenIzq;
        this.tenedorDerecho = tenDer;
    }

    public void run() {
        this.setName("Filosofo " + this.getId());

        System.out.println(this.getName() + " se sentó a la mesa.");

        try {
            do {
                tomarTenedores();
      
                sleep(pausa);
            } while (true);
        } catch (Exception ex) {
            System.out.println("Error...");
        }
    }

    public synchronized void pensar() {
        try {
            System.out.println(this.getName() + " esta piensando mientras espera los tenedores...");
            wait();
        } catch (InterruptedException ex) {}
    }
    
    public void tomarTenedores() {
        if (!tenedorIzquierdo.estaEnUso()) {
            tenedorIzquierdo.usar();
            System.out.println(this.getName() + " tomó el tenedor izquierdo...");        

            if (!tenedorDerecho.estaEnUso()) {
                tenedorDerecho.usar();
                System.out.println(this.getName() + " tomó el tenedor derecho...");        

                this.comer();
            } else {
                tenedorIzquierdo.liberar();
                System.out.println("El tenedor derecho esta siendo usado. " + this.getName() + " liberó su tenedor izquierdo...");                        
                this.pensar();
            }
        }
    }

    public synchronized void comer() {   
        try {
            System.out.println(this.getName() + " esta comiendo...");
                      
            sleep(pausa);

            this.liberarTenedores();
        } catch (InterruptedException ex) {}
    }

    public synchronized void liberarTenedores() {
        tenedorIzquierdo.liberar();
        tenedorDerecho.liberar();
        System.out.println(this.getName() + " liberó los tenedores, y empezó a piensar...");
        notifyAll();
    }
}