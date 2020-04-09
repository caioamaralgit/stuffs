import java.io.*;
import java.net.*;

public class ClienteTCP {
    private static InetAddress host;
    private static final int PORT = 5000;

    public static void main (String[] args) {
        System.out.println("Abriendo puerto\n");
        try {
            host = InetAddress.getByName("192.168.1.82");
        } catch (UnknownHostException e) {
            System.out.println("No se encontró Host!");
            System.exit(1);
        }

        run();
    }

    private static void run() {
        Socket sock = null;

        try {
            sock = new Socket(host, PORT);

            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String fileName, response, content = "";
            
            System.out.println("Cual archivo desea solicitar? ");
            fileName = reader.readLine();
            out.println("{ \"archive\" : \"" + fileName + "\" }");
            
            do {
                response = in.readLine();
                content += (response == null ? "" : response + "\n");
            } while (response != null);

            System.out.println("Donde salvar el archivo? ");
            String route = reader.readLine();

            PrintWriter writer = new PrintWriter(route + "/" + fileName, "UTF-8");
            writer.print(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}