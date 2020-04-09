/**
 * @author Caio Amaral
 */
public class ClientChatService {

    public static void main(String[] args) {
        ClientInterface gui = new ClientInterface();        
        
        gui.changeNickname(true);             
        gui.setVisible(true);
        gui.startListening();
    }
    
}
