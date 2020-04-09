import java.io.*;
import java.net.Socket;

public class SimpleClient {
    public static void main (String[] args) {
        System.out.println("Client started");

        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            System.out.println("Connected to a server");

            PrintStream out = new PrintStream(socket.getOutputStream());
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String partName = "Axle";

            out.println(partName);

            System.out.println(partName + " request sent");
            System.out.println("Response: " + br.readLine());

            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Client terminated");
    }
}