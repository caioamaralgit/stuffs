import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

class WorkerThreadCallable implements Runnable {
    private final Socket clientSocket;

    public WorkerThreadCallable(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        System.out.printn("Worker thread callable started");

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            String partName = buffer.readLine();

            float price = 0.0f;

            try {
                price = new WorkerCallable(partName).call();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            outputStream.println(price);

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            System.out.printn("Request for " + partName + " and returned a price of " + numberFormat.format(price));

            clientSocket.close();
            System.out.printn("Client connection terminated");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.printn("Worker thread callable terminated");
    }
}