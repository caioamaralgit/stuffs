package server;
 
import java.io.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
// Server class
public class Server 
{ 
    static Vector<ClientHandler> clientsList = new Vector<>();
    static int clientsNumber = 0;
 
    public static void main(String[] args) throws IOException 
    {
        ServerSocket serverSock = new ServerSocket(1234);         
        Socket sock;
        
        System.out.println("* Server started.");
        System.out.println("* Waiting for clients...\n");
        
        while (true) 
        {
            sock = serverSock.accept();
 
            System.out.println("New client request received : " + sock);
             
            DataInputStream dis = new DataInputStream(sock.getInputStream());
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
             
            System.out.println("Creating a new handler for this client...");
 
            ClientHandler client = new ClientHandler(sock, clientsNumber, dis, dos);
            Thread clientThread = new Thread(client);
             
            System.out.println("Adding this client to active client list.");
 
            clientsList.add(client);
 
            clientThread.start();
            
            System.out.println("Client ready!\n");
            
            clientsNumber++;
        }
    }
}

// ClientHandler class
class ClientHandler implements Runnable 
{
    Socket sock;
    boolean isloggedin;
    private String name;
    private int id;
    final DataInputStream input;
    final DataOutputStream output;
     
    public ClientHandler(Socket sock, int id, DataInputStream input, DataOutputStream output) {
        this.input = input;
        this.output = output;
        this.name = "Client #" + id;
        this.id = id;
        this.sock = sock;
        this.isloggedin = true;
    }
    
    public void sendMessage(String message) {
        try {
            output.writeUTF(message + "<br />");
        } catch (IOException ex) { }
    }
    
    public void sendUsersList() {
        StringBuilder response = new StringBuilder("");
        
        for (ClientHandler user : Server.clientsList) {
            if (user.id != this.id)
                response.append((response.toString().equals("") ? "users-list:::" : "###") + user.id + ";" + user.name + ";" + user.isloggedin);
        }
        
        if (response.toString().equals("")) response.append("users-list:::0;There's no other users;false");
        
        try {
            output.writeUTF(response.toString());
        } catch (IOException ex) { }
    }
    
    public void listenClient() {
        String recibido;
        String recibidoParseado[] = { "", "" };
        String message[] = { "", "" };
        
        do {
            try {
                recibido = input.readUTF();
                recibidoParseado = recibido.split(":::");
                
                switch (recibidoParseado[0]) {
                    case "new-nickname":
                        recibidoParseado[1] = recibidoParseado[1].equals("Anonimo") ? "Anonimo " + this.name.split("-")[1] : recibidoParseado[1];
                        System.out.println(" * " + this.name + " cambió el nickname para " + recibidoParseado[1] + ".\n");
                        this.name = recibidoParseado[1];
                        
                        for (ClientHandler user : Server.clientsList) {
                            user.sendUsersList();
                        }
                        
                        break;
                    case "nickname": 
                        System.out.println(" * " + this.name + " cambió el nickname para " + recibidoParseado[1] + ".\n");
                        this.name = recibidoParseado[1];
                        
                        for (ClientHandler user : Server.clientsList) {
                            user.sendUsersList();
                        }                        
                        
                        break;
                    case "request-users-list":
                        sendUsersList();
                        
                        break;
                    case "send-message":
                        message = recibidoParseado[1].split("###");
                        
                        for (ClientHandler mc : Server.clientsList) {
                            if (Integer.toString(mc.id).equals(message[0]) && mc.isloggedin) {
                                mc.output.writeUTF("receive-message:::" + id + "###" + this.name + "###" + message[1]);
                                break;
                            }
                        }
                        
                        break;
                }
            } catch (IOException ex) { };
        } while (!recibidoParseado[0].equals("exit"));
    }
    
    @Override
    public void run() {        
        try {
            listenClient();
            isloggedin = false;
            
            for (ClientHandler user : Server.clientsList) {
                user.sendUsersList();
            }
            
            sock.close();
            input.close();
            output.close();
        } catch (IOException ex) {
            System.out.println("* An error ocurred with " + name);
        }
    }
}