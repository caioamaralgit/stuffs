import java.io.*;
import java.util.*;
import java.net.Socket;
import java.text.NumberFormat;
import static java.lang.System.out;

public class WorkerThread implements Runnable {
    // Se declara la instancia del ConcurrentHashMap
    private static final ConcurrentHashMap<String, Float> map;
    private final Socket clientSocket;

    // Se inicializa el ConcurrentHasMap en un bloque de inicialización estático
    static {
        map = new ConcurrentHashMap<>();
        map.put("Axle", 238.50f);
        map.put("Gear", 45.55f);
        map.put("Wheel", 86.30f);
        map.put("Rotor", 8.50f);
    }    

    // El constructor de la clase asigna el socket del cliente
    // a la variable de instancia clientSocket
    public WorkerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    // El método run procesa la solicitud 
    public void run() {
        System.out.println("Worker Thread Started");

        // Se obtiene un input stream desde el socket del cliente y
        try (BufferedReader bis = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            // se usa para obtener el nombre de la autoparte
            String partName = bis.readLine();
            float price = map.get(partName);
            out.println(price);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            System.out.println("Request for " + partName + " and returned a price of " + nf.format(price));
            clientSocket.close();
            System.out.println("Client connection terminated");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Worker Thread Terminated");
    }
}