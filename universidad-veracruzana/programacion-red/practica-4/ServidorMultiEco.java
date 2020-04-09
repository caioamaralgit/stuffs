import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.*;

public class ServidorMultiEco {
    private static ServerSocket socketServidor;
    private static final int PUERTO = 1234;

    public static void main(String[] args) throws IOException {
        try {
            socketServidor = new ServerSocket(PUERTO);
        } catch (IOException ioEx) {
            System.out.println("\n No fue posible conectarse al puerto!");
            System.exit(1);
        }

        do {
            // Esperar por el cliente
            Socket cliente = socketServidor.accept();
            System.out.println("\nSe ha aceptado un cliente nuevo.\n");
            // Crear un hilo para manejar la comunicación con este cliente y passar el 
            // Constructor para este hilo una referencia al socket relevante
            ManejadorCliente manejador = new ManejadorCliente(cliente);
            manejador.start(); // Se llama al método run
        } while (true);
    }
}