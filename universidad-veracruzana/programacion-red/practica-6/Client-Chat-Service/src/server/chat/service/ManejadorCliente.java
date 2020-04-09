package server.chat.service;

import java.io.*;
import java.net.*;
import java.util.*;

class ManejadorCliente extends Thread {
    private Socket cliente;
    private Scanner input;
    private PrintWriter output;
    private ArrayList<ManejadorCliente> clientsList = new ArrayList<ManejadorCliente>();
    private String color;

    public ManejadorCliente(Socket socket) {
        cliente = socket;
        generarColor();

        try {
            input = new Scanner(cliente.getInputStream());
            output = new PrintWriter(cliente.getOutputStream(), true);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
    
    public void avisarTodos(String mensaje) {
        for (ManejadorCliente cliente: clientsList) {
            cliente.enviarMensaje(mensaje);
        }
    }
    
    public void definirListaClientes(ArrayList<ManejadorCliente> cl) {
        clientsList = cl;
    }

    public void enviarMensaje(String mensaje) {
        output.println(mensaje + "<br />");
    }
    
    public void escucharCliente() {
        String recibido;
        String[] recibidoParseado;
        
        do {
            recibido = input.nextLine();
            recibidoParseado = recibido.split(":");
            
            if (recibidoParseado[0].equals("new-nickname")) {
                recibidoParseado[1] = recibidoParseado[1].equals("Anonimo") ? "Anonimo " + this.getName().split("-")[1] : recibidoParseado[1];
                
                System.out.println(" * El nuevo cliente se llama " + recibidoParseado[1] + ".");
                this.setName(recibidoParseado[1]);
                
                avisarTodos("<i>// <span style=\"color: " + color + ";\">" + this.getName() + "</span> entró en la sala.</i>");
            } else if (recibidoParseado[0].equals("nickname")) {
                System.out.println(" * " + this.getName() + " cambió el nickname para " + recibidoParseado[1] + ".");
                this.setName(recibidoParseado[1]);
                
                avisarTodos("<i>// <span style=\"color: " + color + ";\">" + this.getName() + "</span> cambió el nickname para <span style=\"color: " + color + ";\">" + recibidoParseado[1] + "</span>.</i>");
            } else if (!recibidoParseado[0].equals("exit")) {
                System.out.println(this.getName() + ": " + recibidoParseado[1]);
                
                avisarTodos("<span style=\"color: " + color + ";\">" + this.getName() + ":</span> " + recibidoParseado[1]);
            }
        } while (!recibidoParseado[0].equals("exit"));
    }
    
    public void generarColor() {
        Random rand = new Random();
        
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        
        color = "rgb(" + r + ", " + g + ", " + b + ")";
    }

    public void run() {
        output.println("=============================<br />");
        output.println("* SERVIDOR: Bienvenido! <br />");
        output.println("=============================<br /><br />");
        
        escucharCliente();

        try {
            System.out.println(" * " + this.getName() + " cerró la sessión.");
            avisarTodos("<i>// <span style=\"color: " + color + ";\">" + this.getName() + "</span> salió del chat.</i>");
            cliente.close();
        } catch (IOException ioEx) {
            System.out.println(" * Un error ocurrió cuando " + this.getName() + " intentó se disconectar!");
        }
    }    
}