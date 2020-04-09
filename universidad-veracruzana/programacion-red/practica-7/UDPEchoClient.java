import java.io.*;
import java.net.*;

// Clase UDP del Cliente
public class UDPEchoClient {
    // IP del servidor
    private static InetAddress host;
    // Puerto del servidor
    private static final int PORT = 1234;
    // Socket datagrama
    private static DatagramSocket dgramSocket;
    // Paquetes de entrada y salida
    private static DatagramPacket inPkt, outPkt;
    // Buffer de los paquetes
    private static byte[] buff;
    // Para almacenar el contenido de los mensajes
    private static String msg = "", msgIn = "";

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
            // Obtene la dirección IP del servidor
        } catch (UnknownHostException e) {
            // Manejar excepción
            System.out.println("No se encontró el Host!");
            System.exit(1);
        }
        run();
    }

    private static void run() {
        try {
            // Crear el socket datagrama
            dgramSocket = new DatagramSocket();
            // Crear el lector del buffer para leer la consola
            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.print("Ingresar mensaje: ");
                // Leer la entrada del usuario
                msg = userEntry.readLine();

                // Enviar mensajes hasta recibir BYE
                if (!msg.equals("BYE")) {
                    // Crear el paquete
                    outPkt = new DatagramPacket(msg.getBytes(), msg.length(), host, PORT);
                    // Enviar el paquete
                    dgramSocket.send(outPkt);
                    // Asignar el buffer del paquete
                    buff = new byte[256];
                    // Crear el paquete de llegada
                    inPkt = new DatagramPacket(buff, buff.length);
                    // Recibir el paquete de llegada
                    dgramSocket.receive(inPkt);
                    // Almacenar el contenido del paquete
                    msgIn = new String(inPkt.getData(), 0, inPkt.getLength());
                    System.out.println("SERVIDOR: " + msgIn);
                }
            } while (!msg.equals("BYE"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el socket y liberar sus recursos
            dgramSocket.close();
        }
    }
}