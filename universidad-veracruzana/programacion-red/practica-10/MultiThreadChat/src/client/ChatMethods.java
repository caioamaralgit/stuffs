package client;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Caio Amaral
 */
public class ChatMethods {
    private static InetAddress host;
    private static final int ServerPort = 1234;
    private static Socket socket;
    private static DataInputStream input;
    private static DataOutputStream output;
    
    public String init() {
        String response = "";
        
        response += (connect() ? "Connecting to the server...<br />" : "Host not found!<br />");
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
            socket = new Socket(host, ServerPort);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public void disconnect() {
        try {
            output.writeUTF("exit:::true");
            socket.close();
        } catch (IOException ioEx) {
            System.exit(1);
        }
    }
    
    public void changeNickname(String nickname, boolean firstChange) {
        nickname = nickname.trim().equals("") ? "Anonimo" : nickname;
        
        try {
            output.writeUTF((firstChange ? "new-" : "") + "nickname:::" + nickname);            
        } catch (IOException ex) { }
    }

    public void sendMessage(String userId, String message) {
        try {
            output.writeUTF("send-message:::" + userId + "###" + message);            
        } catch (IOException ex) { }
    }
    
    public ArrayList<String[]> listenServer() {
        ArrayList<String[]> response = new ArrayList<String[]>();
        
        try {
            String rawResponse[] = input.readUTF().split(":::");    

            response.add(new String[]{ rawResponse[0] });

            switch (rawResponse[0]) {
                case "receive-message":                     
                    response.add(rawResponse[1].split("###"));
                    
                    break;
                case "users-list":
                    for (String userData : rawResponse[1].split("###")) {
                        response.add(userData.split(";"));
                    }

                    break;
            }
        } catch (IOException ex) { }
        
        return response;
    }
}
