import java.io.*;
import java.net.*;
import java.util.*;

public class ClienteEcoConTCP {
    private static InetAddress host;
    private static final int PUERTO = 1234;

    public static void main(String[] args) {
        try { 
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("ID de host no encontrado.");
            System.exit(1);
        }

        conectarAlServidor();
    }

    private static void conectarAlServidor() {
        Socket enlace = null;
        try {
            enlace = new Socket(host, PUERTO);
            Scanner entrada = new Scanner(enlace.getInputStream());
            PrintWriter salida = new PrintWriter(enlace.getOutputStream(), true);
            Scanner entradaDelUsuario = new Scanner(System.in);
            String mensaje, respuesta;

            do {
                System.out.println("Ingresar mensaje: ");
                mensaje = entradaDelUsuario.nextLine();
                salida.println(mensaje);
                respuesta = entrada.nextLine();
                System.out.println("\nSERVIDOR> " + respuesta);
            } while (!mensaje.equals("---CERRAR---"));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally { 
            try {
                System.out.println("\n* Cerrando la conexión... *");
                enlace.close();               
            } catch (IOException ioEx) {
                System.out.println("Imposible desconectarse.");
                System.exit(1);
            }
        }
    }
}