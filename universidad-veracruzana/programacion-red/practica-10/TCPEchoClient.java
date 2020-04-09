import java.io.*;
import java.net.*;

// Cliente Eco TCP 
public class TCPEchoClient {
    // IP del servidor
    private static InetAddress host;
    // Puerto del servidor 
    private static final int PORT = 1234;

    public static void main (String[] args) {
        System.out.println("Abriendo puerto\n");
        try {
            // Crear el objeto de dirección IP del servidor
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("No se encontró Host!");
            System.exit(1);
        }

        run();
    }

    private static void run() {
        Socket sock = null;

        try {
            // Crear el socket de datos
            sock = new Sockect(host, PORT);
            // Crear el lector y escritor de socket
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            // Configurar para la entrada del usuario
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // Almacenamiento para el mensaje y respuesta
            String msgOut, msgIn;

            do {
                System.out.println("Ingresar mensaje: ");
                // Leer mensaje del usuario
                msgOut = reader.readLine();
                // Enviar el mensaje
                out.println(msgOut);
                // Leer la respuesta
                msgIn = in.readLine();
                System.out.println("SERVIDOR>" + msgIn);
            } while (!msgOut.equals("BYE"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el socket de datos
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}