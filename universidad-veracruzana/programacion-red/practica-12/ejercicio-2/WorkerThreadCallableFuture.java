import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;

import javax.naming.ldap.SortKey;

import com.sun.corba.se.impl.orbutil.closure.Future;
import com.sun.istack.internal.logging.Logger;

class WorkerThreadCallabeFuture implements Runnable {
    private final Socket clientSocket;

    public WorkerThreadCallabeFuture (Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        System.out.println("Worker thread callable started");

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            String partName = buffer.readLine();

            float price = 0.0f;
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

            Future<Float> firstFuture = executor.submit(new Callable<Float>() {
                @Override
                public Float call() throws Exception {
                    // Compute first work
                    return 1.0f;
                }
            });

            Future<Float> secondFuture = executor.submit(new Callable<Float>() {
                @Override
                public Float call() throws Exception {
                    // Compute second part
                    return 2.0f;
                }
            });

            try {
                Float firstPart = firstFuture.get();
                Float secondPart = secondFuture.get();

                price = firstPart + secondPart;
            } catch (InterruptedException | ExecutionException exception) {
                exception.printStackTrace();
            }

            outputStream.println(price);

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            System.out.println("Request for " + partName + " and returned a price of " + numberFormat.format(price));

            clientSocket.close();
            System.out.println("Client connection terminated");
        } catch (IOException ex) {
            Logger.getLogger(SimpleMultiThreadedServer.class.getName().log(Level.SEVERE, null, ex));
        }

        System.out.println("Worker thread callable terminated");
    }
}