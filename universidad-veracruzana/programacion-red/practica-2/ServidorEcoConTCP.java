import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorEcoConTCP {
    private static ServerSocket socketServidor;
    private static final int PUERTO = 1234;

    public static void main(String[] args) {
        System.out.println("Abriendo el puerto...\n");

        try {
            socketServidor = new ServerSocket(PUERTO);
        } catch (IOException ioEx) {
            System.out.println("No fue posible conectarse al puerto.");
            System.exit(1);
        }

        do {
            manejarCliente();
        } while(true);
    }

    private static void manejarCliente() {
        Socket enlace = null;

        try {
            enlace = socketServidor.accept();
            Scanner entrada = new Scanner(enlace.getInputStream());
            PrintWriter salida = new PrintWriter(enlace.getOutputStream(), true);
            int numMensajes = 0;
            String mensaje = entrada.nextLine();
            
            while (!mensaje.equals("---CERRAR---")) {
                System.out.println("Mensaje recebido.");
                numMensajes++;
                salida.println("Mensaje " + numMensajes + ": " + mensaje);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n* Cerrando la conexión... *");
                enlace.close();
            } catch (IOException ioEx) {
                System.out.println("Imposible de desconectar.");
                System.exit(1);
            }
        }
    }
}
