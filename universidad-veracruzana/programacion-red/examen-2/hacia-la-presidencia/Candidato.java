import java.io.*;
import java.net.Socket;

public class Candidato {
    private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));        
    private static BufferedReader outputOfServer;
    private static PrintStream inputIntoServer;
    private static boolean continuar = true;

    public static void main (String[] args) {
        System.out.println(" * Iniciando...");

        String nombre = "Candidato";

        try {
            System.out.println(" Digite su nombre: \n\n");
            nombre = entrada.readLine();
        } catch (Exception ex) {}
        
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            outputOfServer = new BufferedReader(isr);
            inputIntoServer = new PrintStream(socket.getOutputStream());

            inputIntoServer.println("/name " + nombre);
T
            System.out.println("\n * Conectado al servicio de votación!");
            
            Thread escucharComandos = new Thread(new Runnable() {
                @Override 
                public void run() {
                    escucharServidor();
                }
            });
            escucharComandos.start();

            enviarComandos();

            socket.close();
        } catch (IOException ex) {
            System.out.println(" * No fue possible conectarse al sistema de votación!");
        }

        System.out.println(" Encerrando sistema de votación...");
    }

    private static int escucharServidor() {        
        System.out.println(" * Escuchando servidor!");

        do {
            try {
                System.out.println(outputOfServer.readLine());
            } catch (Exception ex) {}
        } while (continuar);

        return 0;
    }

    private static void enviarComandos() {
        System.out.println("\n * El programa está escuchando los comandos.\n");

        do {
            try {
                System.out.println(" *****************************************************************");
                System.out.println(" * Comandos posibles:                                            *");
                System.out.println(" *                                                               *");
                System.out.println(" * /list - Ver candidatos disponibles                            *");
                System.out.println(" * /vote <number> - Votar en el candidato                        *");
                System.out.println(" * /quit - Salir de la votación (nadie más podrá votar en usted) *");
                System.out.println(" *                                                               *");
                System.out.println(" *****************************************************************\n");
                System.out.println(" Los resultados seran mostrados cuando todos los candidatos conectados votaren.\n");
    
                String comando = entrada.readLine();
                inputIntoServer.println(comando);
                
                if (comando.equals("/quit")) {
                    entrada.close();
                    continuar = false;
                }    
            } catch (IOException ioEx) {
                System.out.println(" * Ocurrió un error! :(");
             };
        } while (continuar);
    }
}