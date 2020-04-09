import java.io.*;
import java.net.*;
import java.util.*;

public class ClienteEcoConUDP {
    private static InetAddress host;
    private static final int PUERTO = 1234;
    private static DatagramSocket socketDatagrama;
    private static DatagramPacket paqueteEntrada, paqueteSalida;
    private static byte[] buffer;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("ID de host no encontrado!");
            System.exit(1);
        }

        conectarAlServidor();
    }
  
    private static void conectarAlServidor() {
        try {
            socketDatagrama = new DatagramSocket();
            Scanner entradaUsuario = new Scanner(System.in);
            String mensaje = "", respuesta = "";

            do {
                System.out.println("Ingresar mensaje: ");
                mensaje = entradaUsuario.nextLine();
                if (!mensaje.equals("---CERRAR---")) {
                    paqueteSalida = new DatagramPacket(mensaje.getBytes(), mensaje.length(), host, PUERTO);
                    socketDatagrama.send(paqueteSalida);
                    buffer = new byte[256];
                    paqueteEntrada = new DatagramPacket(buffer, buffer.length);
                    socketDatagrama.receive(paqueteEntrada);
                    respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                    System.out.println("\nSERVIDOR> " + respuesta);
                }
            } while (!mensaje.equals("---CERRAR---"));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            System.out.println("\n* Cerrando la conexión... *");
            socketDatagrama.close();
        }
    }
} 