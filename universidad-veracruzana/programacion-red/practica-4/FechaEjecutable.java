import java.util.Date;
// Imprimir la fechar y hora en intervalos aleatorios.

class FechaEjecutable implements Runnable {
    // Fecha actual
    private Date fechaYHora;
    // Número de veces que se quiere imprimir la fecha y hora.
    private static final int VECES = 10;

    // Constructor
    public FechaEjecutable(Date unaFecha) {
        fechaYHora = unaFecha;
    }

    // run() es el método que hace la tarea del hilo.
    public void run() {
        for (int i = 0; i < VECES; i++) {
            try {
                Date fechaYHoraActual = new Date();
                System.out.println("Iniciado: " + fechaYHora + "/ Ahora: " + fechaYHoraActual);
                // Generar un tiempo para dormir aleatorio.
                int pausa = (int)(Math.random() * 3000);
                // The thread will sleep.
                Thread.sleep(pausa);
            } catch (InterruptedException inEx) {
                // En caso de ser necesario
                System.out.println(inEx.toString());
            }
        }
    }
}