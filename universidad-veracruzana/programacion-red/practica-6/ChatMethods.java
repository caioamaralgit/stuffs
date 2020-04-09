import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author Caio Amaral
 */
public class ChatMethods {
    private static InetAddress host;
    private static final int PUERTO = 1234;
    private static Socket socket;
    private static Scanner entradaRed;
    private static PrintWriter salidaRed;
    private static Scanner entradaDelUsuario;
    
    public String init() {
        String response = "";
        
        response += (connect() ? "Connected to the server...<br />" : "Host not found!<br />");
        response += (initializeSocket() ? "Socket ready.<br /><br />" : "Connected to the server, but cannot create the socket!<br /><br />");
        
        return response;
    }

    public boolean connect() {
        try {
            host = InetAddress.getLocalHost();
            return true;
        } catch (UnknownHostException uhEx) {
            return false;
        }
    }
    
    public boolean initializeSocket() {
        try {
            socket = new Socket(host, PUERTO);
            entradaRed = new Scanner(socket.getInputStream());
            salidaRed = new PrintWriter(socket.getOutputStream(), true);
            entradaDelUsuario = new Scanner(System.in);
            
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public void disconnect() {
        try {
            salidaRed.println("exit:true");
            socket.close();
        } catch (IOException ioEx) {
            System.exit(1);
        }
    }
    
    public void changeNickname(String nickname, boolean firstChange) {
        nickname = nickname.trim().equals("") ? "Anonimo" : nickname;
        salidaRed.println((firstChange ? "new-" : "") + "nickname:" + nickname);
    }

    public void sendMessage(String message) {
        salidaRed.println("message:" + message);
    }
    
    public String listenServer() {
        return entradaRed.nextLine();
    }
}
