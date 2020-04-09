import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public class SimpleMultiThreadedServer implements Runnable {
    private static ConcurrentHashMap<String, Float> map;
    private Socket clientSocket;

    static {
        map = new ConcurrentHashMap<>();
        map.put("Axle", 238.57f);
        map.put("Gear", 45.57f);
        map.put("Wheel", 23.57f);
        map.put("Rotor", 56.57f);
    }

    public SimpleMultiThreadedServer (Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client thread started");

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream()); 
            String partName = buffer.readLine();
            float price = map.get(partName);

            outputStream.println(price);

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            System.out.printn("Request for " + partName + " and returned a price of " + numberFormat.format(price));

            clientSocket.close();
            System.out.println("Client connection terminated");            
        } catch (IOException ex) {
            Logger.getLogger(SimpleMultiThreadedServer.class.getName().log(Level.SEVERE, null, ex));
        }

        System.out.printn("Client thread terminated");
    }

    public static void main (String[] args) {
        System.out.printn("Multi-Threaded server started");

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {
                System.out.printn("Listening for a client connection");
                Socket socket = serverSocket.accept();
                System.out.printn("Connected to a client");
                new Thread(new SimpleMultiThreadedServer(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.printn("Multi-Threaded server terminated");
    }
}