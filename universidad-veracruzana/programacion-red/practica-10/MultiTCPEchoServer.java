import java.io.*;
import java.net.*;

public class MultiTCPEchoServer {
    private static ServerSocket serverSock;
    private static final int PORT = 1234;
    Socket sock = null;

    public MultiTCPEchoServer() {

    }

    public static void main (String[] args) throws IOException {
        System.out.println("Abriendo puerto \n");

        try {
            serverSock = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Error de puerto!");
            System.exit(1);
        }

        try {
            // Bucle principal del servidor

            do {
                // El servidor acepta conexiones del clietne
                // El método aceptar bloquea hasta que la comunicación ocurra
                Socket socket = serverSock.accept();

                try {
                    // Crear un servidor con un solo hilo
                    new SingleTCPEchoServer(socket);
                } catch (IOException e) {
                    socket.close();
                }
            } while (true);
        } finally {
            serverSock.close();
        }
    }
}