// Java implementation for multithreaded chat client
// Save file as Client.java

package client;
 
public class Client 
{
    public static void main(String args[])
    {        
        ClientInterface gui = new ClientInterface();        
        
        gui.changeNickname(true);             
        gui.setVisible(true);
        gui.startListening(); 
    }
}