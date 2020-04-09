class MensajeEjecutable implements Runnable {
    // Mensaje to be printed.
    private String mensaje;
    // Número de veces que se quierer imprimir un mensaje.
    private static final int VECES = 10;

    // Constructor
    public MensajeEjecutable(String unMensaje) {
        mensaje = unMensaje;
    }

    // run() es el método que hace la tarea del hilo.
    public void run() {
        for (int i = 0; i < VECES; i++) {
            try {
                System.out.println(mensaje);
                // Generar un tiempo para dormir aleatorio.
                int pausa = (int)(Math.random() * 3000);
                // El hijo se dormirá.
                Thread.sleep(pausa);
            } catch (InterruptedException inEx) {
                // En caso de ser necesario.
                System.out.println(inEx.toString());
            }
        }
    }
}