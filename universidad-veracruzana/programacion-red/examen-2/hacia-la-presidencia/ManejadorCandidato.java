import java.io.*;
import java.net.*;
import java.util.*;

class ManejadorCandidato extends Thread {
    private Socket candidato;
    private PrintWriter output;
    private Scanner input;

    public boolean concorrendo = true;
    public boolean voto = false;
    public int numero = 0;
    public int votos = 0; 

    public ManejadorCandidato(Socket socket) {
        candidato = socket;

        try {
            input = new Scanner(candidato.getInputStream());
            output = new PrintWriter(candidato.getOutputStream(), true);
        } catch (IOException ioEx) {
            System.out.println(" * Error al iniciar candidato!\n");
            ioEx.printStackTrace();
        }
    }

    public void votarCandidado(int candidato) {
        if (candidato != this.numero) {
            Server.votarCandidato(candidato);
            this.voto = true;
        } else {
            output.println(" * No se puede votar en si mismo!");
        }
    }

    public void abandonarContienda() {
        this.concorrendo = false;
    }

    public void enviarMensaje(String mensaje) {
        output.println(mensaje);
    }
    
    public void escucharCandidato() {
        output.println(" * Registrado en el servidor.");
        
        String recibido;
        
        do {
            recibido = input.nextLine();
            
            switch (recibido.substring(0, 4)) {
                case "/name": 
                    this.numero = Integer.parseInt(this.getName().split("-")[1]);
                    this.setName(recibido.substring(6, recibido.length() - 1));
                    output.println(" * Bienvenido, " + this.getName() + "!");
                    break;
                case "/list": 
                    Server.mostrarListaCandidatos(output);
                    break;
                case "/vote": 
                    votarCandidado(Integer.parseInt(recibido.substring(6, recibido.length() - 1)));
                    break;
                case "/quit": 
                    abandonarContienda();
                    break;
                default: 
                    // Comando desconocido
            }
        } while (concorrendo);
    }

    public void run() {        
        escucharCandidato();

        try {
            candidato.close();
        } catch (IOException ioEx) {
            System.out.println(" * Un error ocurrió cuando " + this.getName() + " intentó se disconectar!");
        }
    }    
}