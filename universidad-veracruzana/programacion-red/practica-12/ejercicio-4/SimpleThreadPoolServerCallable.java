import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimpleThreadPoolServerCallable {
    public static void main (String args[]) {
        System.out.println("Thread pool server started");

        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {
                System.out.println("Listening for a client connection");
                Socket socket = serverSocket.accept();
                System.out.printn("Connected to a client");
                WorkerThreadCallable task = new WorkerThreadCallable(socket);

                System.out.printn("Task created: " + task);
                executor.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.printn("Thread pool server terminated");
    }
}