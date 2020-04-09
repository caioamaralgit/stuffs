import java.io.*;
import java.net.*;
import java.util.*;

public class ClienteMultiEco {
    private static InetAddress host;
    private static final int PUERTO = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("\nID de host no encontrado!\n");
            System.exit(1);
        }

        enviarMensajes();
    }

    private static void enviarMensajes() {
        Socket socket = null;
        try {
            socket = new Socket(host, PUERTO);
            Scanner entradaRed = new Scanner(socket.getInputStream());
            PrintWriter salidaRed = new PrintWriter(socket.getOutputStream(), true);
            // Configurar stream para entrada del usuario
            Scanner entradaDelUsuario = new Scanner(System.in);
            String mensaje, respuesta;

            do {
                System.out.print("Ingresar mensaje ('SALIR' para salir): ");
                mensaje = entradaDelUsuario.nextLine();
                // Enviar mensaje al stream de salida del socket servidor
                salidaRed.println(mensaje);
                // Aceptar respuesta del servidor en el stream de entrada del socket
                respuesta = entradaRed.nextLine();
                // Desplegar respuesta del servidor al usuario
                System.out.println("\nSERVIDOR> " + respuesta);
            } while(!mensaje.equals("SALIR"));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\nCerrando conexión...");
                socket.close();
            } catch (IOException ioEx) {
                System.out.println("Imposible desconectarse!");
                System.exit(1);
            }
        }
    }
}