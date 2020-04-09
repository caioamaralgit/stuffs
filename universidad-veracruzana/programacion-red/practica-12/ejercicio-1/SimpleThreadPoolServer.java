import java.util.*;
import java.net.ServerSocket;

public class SimpleThreadPoolServer {
    public static void main (String[] args) {
        System.out.println("Thread pool server started.");

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {
                System.out.println("Listening for a client connection");
                Socket socket = serverSocket.accept();
                System.out.println("Connected to a client");
                WorkerThread task = new WorkerThread(socket);

                System.out.println("Task created: " + task);
                executor.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Thread pool server terminated.");
    }
}