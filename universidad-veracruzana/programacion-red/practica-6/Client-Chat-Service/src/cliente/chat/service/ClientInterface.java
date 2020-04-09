package cliente.chat.service;

import java.awt.event.*;
import javax.swing.*;

/**
 * @author Caio Amaral
 */
public class ClientInterface extends JFrame {
    
    ChatMethods chat = new ChatMethods();
    String chatMessages = "";
    boolean escucharServidor = true;
    
    public ClientInterface() {
        initComponents();
        chatMessages = chat.init();
        generateChatText();
    }
    
    public void changeNickname(boolean firstChange) {
        String nickname = JOptionPane.showInputDialog("Please, enter your nickname:");
        nickname = nickname.trim().equals("") ? "Anonimo" : nickname;
        
        labelNickname.setText(nickname);
        chat.changeNickname(nickname, firstChange);
    }
    
    public void generateChatText() {
        labelChat.setText("<html><body style=\"padding: 5px;\">" + chatMessages + "</body></html>");
        panelChat.getVerticalScrollBar().setValue(panelChat.getVerticalScrollBar().getMaximum() + 10);
    }
    
    public void startListening() {        
        while (escucharServidor) {
            chatMessages += chat.listenServer();
            generateChatText();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelTitle.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        labelTitle.setText("Chat with Threads");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(labelTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelChat))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNicknameTitle)
                            .addComponent(labelMessageTitle))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNickname))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonChangeNickname)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(buttonSendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelChat, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonChangeNickname)
                    .addComponent(labelNicknameTitle)
                    .addComponent(labelNickname))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMessageTitle)
                    .addComponent(textfieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSendMessage))
                .addGap(19, 19, 19))
        );

        buttonSendMessage.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSendMessageActionPerformed(ActionEvent evt) {//GEN-FIRST:event_buttonSendMessageActionPerformed
        if (!textfieldMessage.getText().equals("")) {
            chat.sendMessage(textfieldMessage.getText());
            textfieldMessage.setText("");
        }
    }//GEN-LAST:event_buttonSendMessageActionPerformed

    private void buttonChangeNicknameMouseClicked(MouseEvent evt) {//GEN-FIRST:event_buttonChangeNicknameMouseClicked
        changeNickname(false);
    }//GEN-LAST:event_buttonChangeNicknameMouseClicked

    private void textfieldMessageKeyPressed(KeyEvent evt) {//GEN-FIRST:event_textfieldMessageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !textfieldMessage.getText().equals("")) {
            chat.sendMessage(textfieldMessage.getText());
            textfieldMessage.setText("");
        }
    }//GEN-LAST:event_textfieldMessageKeyPressed

    private void formWindowClosing(WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        escucharServidor = false;
        chat.disconnect();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonChangeNickname;
    private javax.swing.JButton buttonSendMessage;
    private javax.swing.JLabel labelChat;
    private javax.swing.JLabel labelMessageTitle;
    private javax.swing.JLabel labelNickname;
    private javax.swing.JLabel labelNicknameTitle;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JScrollPane panelChat;
    private javax.swing.JTextField textfieldMessage;
    // End of variables declaration//GEN-END:variables
}
