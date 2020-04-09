import java.io.*;
import java.net.*;

/**
 * ServidorEcoConUDP
 */
public class ServidorEcoConUDP {
    private static final int PUERTO = 1234;
    private static DatagramSocket socketDatagrama;
    private static DatagramPacket paqueteEntrada, paqueteSalida;
    private static byte[] buffer;

    public static void main(String[] args) {
        System.out.println("Abriendo el puerto...");

        try {
            socketDatagrama = new DatagramSocket(PUERTO);
        } catch (SocketException sockEx) {
            System.out.println("No fue posible conectarse al puerto.");
            System.exit(1);
        }

        manejarCliente();        
    }
    
    private static void manejarCliente() {
        try {
            String mensajeEntrada, mensajeSalida;
            int numeroMensajes = 0;
            InetAddress direccionCliente = null;
            int puertoCliente;

            do {
                buffer = new byte[256];
                paqueteEntrada = new DatagramPacket(buffer, buffer.length);
                socketDatagrama.receive(paqueteEntrada);
                direccionCliente = paqueteEntrada.getAddress();
                puertoCliente = paqueteEntrada.getPort();
                mensajeEntrada = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Mensaje recebido.");
                numeroMensajes++;
                mensajeSalida = "Mensaje " + numeroMensajes + ": " + mensajeEntrada;
                paqueteSalida = new DatagramPacket(mensajeSalida.getBytes(), mensajeSalida.length(), direccionCliente, puertoCliente);
                socketDatagrama.send(paqueteSalida);
            } while (true);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            System.out.println("\n* Cerrando conexión... *");
            socketDatagrama.close();
        }
    }
}   