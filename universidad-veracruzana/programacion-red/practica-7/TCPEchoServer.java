import java.io.*;
import java.net.*;

// Servidor Eco basado en TCP
public class TCPEchoServer {
    // Socket servidor
    private static ServerSocket servSock;
    // Server port
    private static final int PORT = 1234;

    public static void main (String[] args) {
        System.out.println("Abriendo puerto\n");

        try {
            // Crear el socket del servidor
            servSock = new ServerSocket(PORT);
        } catch (SocketException e) {
            // Handle potential eceptions
            System.out.println("Error al vincularse al puerto!");
            System.exit(1);
        } catch (IOException e) {
            // Manejar excepciones potenciales
            System.out.println("Error al crear el socket!");
            System.exit(1);
        }

        // Realizar el servidor de echo de manera indefinida
        do {
            run();
        } while (true);
    }

    private static void run() {
        // Socket de datos
        Socket sock = null;

        try {
            // Escuchar las conexiones entrantes
            sock = servSock.accept();
            // Crear el buffer lector del socket
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            // Crear el socket para escribir
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            int numMsgs = 0;
            // Leer datos del socket
            String msg = in.readLine();
            while (!msg.equals("BYE")) {
                System.out.println("Mensaje recibido.");
                numMsgs++;
                out.println("Mensaje " + numMsgs + ": " + msg);
                msg = in.readLine();
            }
            out.println(numMsgs + " mensajes recibidos.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el socket
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}