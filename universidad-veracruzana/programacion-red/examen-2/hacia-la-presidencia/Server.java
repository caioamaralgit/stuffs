import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 5000;
    private static ServerSocket servSocket;
    private static ArrayList<ManejadorCandidato> clientsList = new ArrayList<ManejadorCandidato>();

    public static void main(String[] args) throws IOException {
        System.out.println(" * Iniciando servidor de votación...");
        
        try {
            servSocket = new ServerSocket(PORT);
            System.out.println(" * Escuchando el puerto " + PORT);
        } catch (IOException ioEx) {
            System.out.println(" * No fue posible conectarse al puerto!\n");
            System.exit(1);
        }
    
        do {
            Socket cliente = servSocket.accept();
            System.out.println(" * Se ha aceptado un nuevo candidato.");
            
            ManejadorCandidato manejador = new ManejadorCandidato(cliente);
            clientsList.add(manejador);
            
            manejador.start();       
        } while (true);
    }

    public static void mostrarListaCandidatos(PrintWriter output) {
        StringBuilder respuesta = new StringBuilder("Candidatos: \n\n");

        for (ManejadorCandidato candidato : clientsList) {
            if (candidato.concorrendo) respuesta.append(candidato.numero).append(" - ").append(candidato.getName()).append("\n");
        }

        output.println(respuesta.toString());
    }

    public static void votarCandidato(int numero) {
        boolean finalElecciones = true;

        for (ManejadorCandidato candidato : clientsList) {
            if (candidato.concorrendo && candidato.numero == numero) candidato.votos++;
            if (candidato.concorrendo && !candidato.voto) finalElecciones = false;
        }

        if (finalElecciones) finalizarEleccion();
    }

    public static void finalizarEleccion() {
        String ganador = "";
        int votos = -1;

        for (ManejadorCandidato candidato : clientsList) {
            if (candidato.concorrendo && candidato.votos > votos) {
                ganador = candidato.getName();
                votos = candidato.votos;
            }
        }

        for (ManejadorCandidato candidato : clientsList) {
            candidato.enviarMensaje(" Fim de las elecciones! \n El ganador fue " + ganador + " con " + votos + " votos!");
        }
    }
}