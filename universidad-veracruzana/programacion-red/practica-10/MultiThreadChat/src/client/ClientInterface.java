package client;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author Caio Amaral
 */
public class ClientInterface extends JFrame {
    
    ChatMethods chat = new ChatMethods();
    ArrayList<String[]> usersList = new ArrayList<String[]>();
    ArrayList<String[]> messagesStorage = new ArrayList<String[]>();
    boolean escucharServidor = true;
    
    public ClientInterface() {
        initComponents();
        chat.init();
    }
    
    public void changeNickname(boolean firstChange) {
        String nickname = JOptionPane.showInputDialog("Please, enter your nickname:");
        nickname = nickname.trim().equals("") ? "Anonimo" : nickname;
        
        labelNickname.setText(nickname);
        chat.changeNickname(nickname, firstChange);
    }
    
    private String getSelectedUserId() {
        return usersList.get(comboBoxUser.getSelectedIndex())[0];
    }
    
    public void mountUsersList() {
        StringBuilder nameList = new StringBuilder("");
        int selectedUser = comboBoxUser.getSelectedIndex();
        
        for (String[] userData : usersList) {
            nameList.append(nameList.toString().equals("") ? userData[1] : ";" + userData[1]);
        }
        
        comboBoxUser.setModel(new javax.swing.DefaultComboBoxModel<>(nameList.toString().split(";")));
        comboBoxUser.setSelectedIndex(selectedUser);
        
        showOnlineUsers();
    }
    
    public void showOnlineUsers() {
        StringBuilder onlineUsers = new StringBuilder("<html><body>");
        int unreadMessages = 0;
        
        for (String[] userData : usersList) {
            if (userData[2].equals("true")) {
                unreadMessages = unreadMessagesNumber(userData[0]);
                
                onlineUsers.append("<div style=\"padding: 2px 5px;")
                    .append(unreadMessages > 0 && !talkingToId(userData[0]) ? "color: #00FF00;\">" : "\">")
                    .append(userData[1]);
                
                if (unreadMessages > 0) {
                    onlineUsers.append(" (")
                        .append(unreadMessages)
                        .append(")");
                }
                            
                onlineUsers.append("</div>");       
            }
        }
        
        onlineUsers.append("</body></html>");
        
        labelOnlineUsers.setText(onlineUsers.toString());
    }
    
    public int unreadMessagesNumber(String id) {
        int unreadMessages = 0;
        
        for (String[] messages : messagesStorage) {
            if (messages[0].equals(id)) unreadMessages = Integer.parseInt(messages[2]);
        }
        
        return unreadMessages;
    }
    
    public boolean talkingToId(String id) {
        return getSelectedUserId().equals(id);
    }
    
    public void addMessagetoStore(String[] message, boolean self) {
        int index = -1;
        StringBuilder newMessage = new StringBuilder("<div style=\"padding: 2px 5px; color:");
        
        for (int i = 0; i < messagesStorage.size(); i++) {
            if (messagesStorage.get(i)[0].equals(message[0])) index = i;
        }
        
        if (index == -1) {
            messagesStorage.add(new String[] { message[0], "", "0" });
            index = messagesStorage.size() - 1;
        }
        
        newMessage.append(self ? "#0000FF;\">" : "#FF0000;\">")
            .append(message[1])
            .append(": ")
            .append(message[2])
            .append("</div>");
        
        messagesStorage.get(index)[1] += newMessage.toString();
        messagesStorage.get(index)[2] = Integer.toString(Integer.parseInt(messagesStorage.get(index)[2]) + 1);
    }
    
    public void renderMessages(String id) {
        StringBuilder messages = new StringBuilder("<html><body>");
        
        for (int i = 0; i < messagesStorage.size(); i++) {
            if (messagesStorage.get(i)[0].equals(id)) {
                messages.append(messagesStorage.get(i)[1]);                
                messagesStorage.get(i)[2] = "0";
            }
        }
        
        messages.append("</body></html>");
        
        labelChat.setText(messages.toString());
        panelChat.getVerticalScrollBar().setValue(panelChat.getVerticalScrollBar().getMaximum() + 10);
    }
    
    public boolean userIsOnline(String id) {
        boolean online = false;
        
        for (String[] userData : usersList) {
            if (userData[0].equals(id)) online = Boolean.parseBoolean(userData[2]);
        }
        
        return online;
    }
    
    public void startListening() {      
        ArrayList<String[]> response = new ArrayList<String[]>();
        
        while (escucharServidor) {
            response = chat.listenServer();
            
            switch (response.get(0)[0]) {
                case "users-list": 
                    response.remove(0);                    
                    usersList = response;
                    
                    mountUsersList();
                    
                    break;                    
                case "receive-message": 
                    addMessagetoStore(response.get(1), false);
                    
                    if (talkingToId(response.get(1)[0])) renderMessages(response.get(1)[0]);
                    
                    showOnlineUsers();
                    
                    break;
            }
        } 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        panelChat = new javax.swing.JScrollPane();
        labelChat = new javax.swing.JLabel();
        labelNicknameTitle = new javax.swing.JLabel();
        labelMessageTitle = new javax.swing.JLabel();
        labelNickname = new javax.swing.JLabel();
        textfieldMessage = new javax.swing.JTextField();
        buttonSendMessage = new javax.swing.JButton();
        buttonChangeNickname = new javax.swing.JLabel();
        panelUsers = new javax.swing.JPanel();
        labelUsersTitle = new javax.swing.JLabel();
        panelUsersLists = new javax.swing.JScrollPane();
        labelOnlineUsers = new javax.swing.JLabel();
        labelTakingTo = new javax.swing.JLabel();
        comboBoxUser = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelTitle.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        labelTitle.setText("Multi-Thread Chat");

        panelChat.setBackground(new java.awt.Color(255, 255, 255));

        labelChat.setBackground(new java.awt.Color(255, 255, 255));
        panelChat.setViewportView(labelChat);

        labelNicknameTitle.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        labelNicknameTitle.setText("Nickname:");

        labelMessageTitle.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        labelMessageTitle.setText("Message:");

        labelNickname.setText("---");

        textfieldMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textfieldMessageKeyPressed(evt);
            }
        });

        buttonSendMessage.setText("Send");
        buttonSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendMessageActionPerformed(evt);
            }
        });

        buttonChangeNickname.setFont(new java.awt.Font("Noto Sans", 2, 12)); // NOI18N
        buttonChangeNickname.setForeground(new java.awt.Color(0, 4, 255));
        buttonChangeNickname.setText("Change Nickname");
        buttonChangeNickname.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonChangeNickname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonChangeNicknameMouseClicked(evt);
            }
        });

        panelUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelUsersTitle.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        labelUsersTitle.setText("Connected Users:");

        panelUsersLists.setViewportView(labelOnlineUsers);

        labelTakingTo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        labelTakingTo.setText("Talking To:");

        comboBoxUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--------" }));
        comboBoxUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUsersLayout = new javax.swing.GroupLayout(panelUsers);
        panelUsers.setLayout(panelUsersLayout);
        panelUsersLayout.setHorizontalGroup(
            panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsersLayout.createSequentialGroup()
                .addGroup(panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUsersLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(labelUsersTitle)
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addGroup(panelUsersLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelUsersLists)
                            .addComponent(comboBoxUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(panelUsersLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(labelTakingTo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUsersLayout.setVerticalGroup(
            panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUsersTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelUsersLists, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTakingTo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        labelUsersTitle.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelMessageTitle)
                                    .addGap(22, 22, 22)
                                    .addComponent(textfieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(panelChat, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNicknameTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelNickname)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonChangeNickname)
                                    .addComponent(buttonSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelTitle)
                        .addGap(249, 249, 249))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelChat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonChangeNickname)
                            .addComponent(labelNicknameTitle)
                            .addComponent(labelNickname))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMessageTitle)
                            .addComponent(textfieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSendMessage))))
                .addGap(19, 19, 19))
        );

        buttonSendMessage.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSendMessageActionPerformed(ActionEvent evt) {//GEN-FIRST:event_buttonSendMessageActionPerformed
        if (!textfieldMessage.getText().equals("")) {
            addMessagetoStore(new String[] { getSelectedUserId(), labelNickname.getText(), textfieldMessage.getText() }, true);
            renderMessages(getSelectedUserId());
            chat.sendMessage(getSelectedUserId(), textfieldMessage.getText());
            textfieldMessage.setText("");
        }
    }//GEN-LAST:event_buttonSendMessageActionPerformed

    private void buttonChangeNicknameMouseClicked(MouseEvent evt) {//GEN-FIRST:event_buttonChangeNicknameMouseClicked
        changeNickname(false);
    }//GEN-LAST:event_buttonChangeNicknameMouseClicked

    private void textfieldMessageKeyPressed(KeyEvent evt) {//GEN-FIRST:event_textfieldMessageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !textfieldMessage.getText().equals("")) {
            addMessagetoStore(new String[] { getSelectedUserId(), labelNickname.getText(), textfieldMessage.getText() }, true);
            renderMessages(getSelectedUserId());
            chat.sendMessage(getSelectedUserId(), textfieldMessage.getText());
            textfieldMessage.setText("");
        }
    }//GEN-LAST:event_textfieldMessageKeyPressed

    private void formWindowClosing(WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        escucharServidor = false;
        chat.disconnect();
    }//GEN-LAST:event_formWindowClosing

    private void comboBoxUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUserActionPerformed
        boolean userOnline = userIsOnline(getSelectedUserId());
        
        textfieldMessage.setText("");
        textfieldMessage.setEditable(userOnline);
        buttonSendMessage.setEnabled(userOnline);
        
        renderMessages(getSelectedUserId());
        showOnlineUsers();
    }//GEN-LAST:event_comboBoxUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonChangeNickname;
    private javax.swing.JButton buttonSendMessage;
    private javax.swing.JComboBox<String> comboBoxUser;
    private javax.swing.JLabel labelChat;
    private javax.swing.JLabel labelMessageTitle;
    private javax.swing.JLabel labelNickname;
    private javax.swing.JLabel labelNicknameTitle;
    private javax.swing.JLabel labelOnlineUsers;
    private javax.swing.JLabel labelTakingTo;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelUsersTitle;
    private javax.swing.JScrollPane panelChat;
    private javax.swing.JPanel panelUsers;
    private javax.swing.JScrollPane panelUsersLists;
    private javax.swing.JTextField textfieldMessage;
    // End of variables declaration//GEN-END:variables
}
