import java.io.*;
import java.net.*;
import java.util.*;

class ManejadorCliente extends Thread {
    private Socket cliente;
    private Scanner entrada;
    private PrintWriter salida;

    public ManejadorCliente(Socket socket) {
        // Configurar referencia al socket asociado
        cliente = socket;

        try {
            entrada = new Scanner(cliente.getInputStream());
            salida = new PrintWriter(cliente.getOutputStream(), true);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void run() {
        String recibido;

        do {
            // Aceptar mensaje de cliente en el stream de entrada del socket
            recibido = entrada.nextLine();
            // Regresar el mensaje al cliente como un Eco al stream de salida del socket
            salida.println("ECO: " + recibido);
            // Repetir lo anterior hasta que el cliente mande 'SALIR'
        } while (!recibido.equals("SALIR"));

        try {
            if (cliente != null) {
                System.out.println("Cerrando conexión...");
                cliente.close();
            } 
        } catch (IOException ioEx) {
            System.out.println("Imposible desconectarse!");
        }
    }    
}