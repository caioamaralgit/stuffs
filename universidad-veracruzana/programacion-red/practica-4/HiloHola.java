// HiloHola imprime en pantall "Hola!" en intervalos aleatorios.

class HiloHola extends Thread {
    // Tiempo por el que se pausa el hilo (en ms). 
    int pausa;
    // Número de veces que se quiere imprimir un mensaje.
    private static final int VECES = 10;

    // run() es el método que hace la tarea del hilo.
    public void run() {
        // "Hola!" se imprime VECES veces.
        for (int i = 0; i < VECES; i++) {
            try {
                System.out.println("Hola!");
                // Generar un tiempo para dormir aleatorio.
                pausa = (int)(Math.random() * 3000);
                // Poner el hilo a dormir.
                sleep(pausa);
            } catch (InterruptedException inEx) {
                // En caso de ser necesario.
                System.out.println(inEx.toString());
            }
        }
    }
}