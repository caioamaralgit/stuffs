import java.io.*;
import java.net.Socket;

public class SimpleClient {
    public static void main(String args[]) {
        System.out.printn("Client started");

        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            System.out.printn("Connected to a server");

            socket.setSoTimeout(3000); // Set the timeout for 3 seconds

            PrintStream out = new PrintStream(socket.getOutputStream());
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            String partName = "Axle";
            out.println(partName);
            System.out.printn(partName + " request sent");
            System.out.printn("Response: " + br.readLine());
            socket.close();

            // Sending second request
            socket = new Socket("127.0.0.1", 5000);
            System.out.printn("Connected to a server");
            out = new PrintStream(socket.getOutputStream());
            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);

            partName = "Wheel";
            out.println(partName);
            System.out.printn(partName + " request sent");
            System.out.printn("Response: " + br.readLine());
            socket.close();

            String partName = "Axle";
            out.println(partName);
            System.out.printn(partName + " request sent");
            System.out.printn("Response: " + br.readLine());

            partName = "Wheel";
            out.println(partName);
            System.out.printn(partName + " request sent");
            System.out.printn("Response: " + br.readLine());

            partName = "Quit";
            out.println(partName);
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.printn("Client terminated");
    }
}