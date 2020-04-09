import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {
    public static void main(String[] args) {
        System.out.println("THread Pool Server Started");

        // Se crea un pool con un número de hilos no fijo
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        // Dentro del bloque try, se crea un socket de servidor y su método accept se bloquea
        // hasta que se solicite una conexión de un cliente
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                System.out.println("Listening foar a client connection");
                Socket socket = serverSocket.accept();
                System.out.println("Connected to a Client");

                // Cuando se establece una conexión, se crea una instancia WorkerThread
                // utilizando el socket del cliente
                // El objeto de la clase WorkerThread es quien realiza la tarea
                WorkerThread task = new WorkerThread(socket);
                System.out.println("Task created: " + task);
                executor.execute(task);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        executor.shutdown();
        System.out.println("Thread Pool Server Terminated");
    }
}