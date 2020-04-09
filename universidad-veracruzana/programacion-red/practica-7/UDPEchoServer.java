import java.io.*;
import java.net.*;

import com.sun.istack.internal.FinalArrayList;

// Clase Servidor UDP
public class UDPEchoServer {
    // Puerto utilizado por el servidor
    private static final int PORT = 1234;
    // El socket específico para UDP
    private static DatagramSocket dgramSocket;
    // Objetos para los paquetes de entrada y salida
    private static DatagramPacket inPkt, outPkt;
    // Buffer de paquetes de datos
    private static byte[] buffer;

    public static void main (String[] args) {
        System.out.println("Abriendo el puerto...\n");

        try {
            // Crear el socket de datagramas
            dgramSocket = new DatagramSocket(PORT);            
        } catch (SocketException e) {
            // Manejar excepciones potenciales
            System.out.println("Error al abrir el puerto!");
            System.exit(1);
        }
        run();
    }

    // run() realiza la tarea principal del servidor
    private static void run() {
        try {
            // Buffers para los mensajes a enviar y recibir
            String msgIn, msgOut;
            // Numero de mensajes
            int numMsgs = 0;

            do {
                // Crear el buffer de paquetes
                buffer = new byte[256];
                // Crear el paquete entrante
                inPkt = new DatagramPacket(buffer, buffer.length);
                // Recibir el paquete del cliente
                dgramSocket.receive(inPkt);
                // Obtener la dirección IP del remitente
                InetAddress cliAddress = inPkt.getAddress();
                // Obtener el número de puerto del cliente
                int cliPort = inPkt.getPort();
                // Almacenar el contenido del mensaje
                msgIn = new String(inPkt.getData(), 0, inPkt.getLength());
                System.out.println("Mensaje recibido.");
                // Incrementar el número de mensajes
                numMsgs++;
                // Generar el mensaje de salida
                msgOut = ("Msg " + numMsgs + ": " + msgIn);
                // Crear el paquete de salida
                outPkt = new DatagramPacket(msgOut.getBytes(), msgOut.length(), cliAddress, cliPort);
                // Enviar el paquete de salida al cliente
                dgramSocket.send(outPkt);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el socket y liberar sus recursos
            dgramSocket.close();
        }
    }
}