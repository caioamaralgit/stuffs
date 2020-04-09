import java.io.*;
import java.net.Socket;

// Crear la clase servidor que extiende a Thread
public class SingleTCPEchoServer extends Thread {
    private static Socket sock;
    private static final int PORT = 1234;

    private BufferedReader in;
    private PrintWriter out;

    public SingleTCPEchoServer(Socket s) throws IOException {
        sock = s;
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new PrintWriter(sock.getOutputStream(), true);

        start();
    }

    public void run() {
        try {
            int numMessages = 0;
            String msg = in.readLine();

            while (!msg.equals("BYE")) {
                System.out.println("Mensaje recibido");
                numMessages++;

                // Enviar mensjae de respuesta al cliente
                out.println("Mensaje " + numMessages + ": " + msg);
                // Leer el seguiente mensaje
                msg = in.readLine();
            }

            out.println(numMessages + " mensajes recibidos.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { 
                System.out.println("\n Cerrando la conexión");
                sock.close();
            } catch (IOException e) { 
                System.out.println("Imposible desconectarse.");
                System.exit(1);
            }
        }
    } 
}