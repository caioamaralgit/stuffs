import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorMultiEco extends Thread {
    private static ServerSocket servSocket;
    private static final int PORT = 1234;
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<ManejadorCliente> clientsList = new ArrayList<ManejadorCliente>();

    public static void main(String[] args) throws IOException {
        System.out.println("\n * Servidor iniciado");
        
        try {
            servSocket = new ServerSocket(PORT);
            System.out.println(" * Escuchando el puerto " + PORT);
        } catch (IOException ioEx) {
            System.out.println(" * No fue posible conectarse al puerto!\n");
            System.exit(1);
        }
        
        ServidorMultiEco inputReader = new ServidorMultiEco();
        inputReader.start();

        do {
            Socket cliente = servSocket.accept();
            System.out.println(" * Se ha aceptado un cliente nuevo.");
            
            ManejadorCliente manejador = new ManejadorCliente(cliente);
            clientsList.add(manejador);
            
            manejador.definirListaClientes(clientsList);
            
            manejador.start();       
        } while (true);
    }
    
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));        
        
        try {
            System.out.println(" * Recibindo mensajes en el console.");
            System.out.println(" * Para no leer más mensajes escritas en el console digite '/c quit'.\n");
            
            String line = in.readLine();
            
            while (!line.equals("/c quit")) {
                enviarMensajeClientes(line);
                line = in.readLine();
            }

            in.close();            
        } catch (IOException ioEx) { };
    }
    
    public void enviarMensajeClientes(String mensaje) {
        String mensajeFinal = "<br />=============================<br />";
        mensajeFinal += "* SERVIDOR: " + mensaje + " <br />";
        mensajeFinal += "=============================<br />";
        
        for (ManejadorCliente cliente: clientsList) {
            cliente.enviarMensaje(mensajeFinal);
        }
        
        System.out.println(mensajeFinal.replaceAll("<br />", "\n"));
    }
}