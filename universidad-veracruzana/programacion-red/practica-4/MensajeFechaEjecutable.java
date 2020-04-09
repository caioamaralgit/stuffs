// Hilo principal de la aplicación.
import java.util.Date;

public class MensajeFechaEjecutable {
    public static void main (String[] args) {
        // Crear los objetos
        MensajeEjecutable mensajeEjecutable = new MensajeEjecutable("Hola!");
        FechaEjecutable fechaEjecutable = new FechaEjecutable(new Date());
        // Crear los objetos hilos
        Thread hiloMensaje = new Thread(mensajeEjecutable);
        Thread hiloFecha = new Thread(fechaEjecutable);
        // Iniciar los hilos
        hiloMensaje.start();
        hiloFecha.start();
    }
}