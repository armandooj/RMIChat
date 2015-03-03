/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.List;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChatRoom;

/**
 *
 * @author administrador
 */
public class ClientUI extends Frame {
    
    Client client;
    TextField serverport, username;
    Button connect, disconnect;
    TextField message;
    Button send;
    TextArea fromserver;
    String title;
    List userList = new List();
    public static ClientUI clientUI;
    
    public static void main(String[] args) {
        clientUI = new ClientUI();
        clientUI.createUI("Chat");
    }    
    
    public void addUser(String user) {
        userList.add(user);
    }
    
    public void removeUser(String user) {
        userList.remove(user);
    }
    
    public void addMsg(String msg) {
        fromserver.setText(fromserver.getText() + msg + "\n");
    }

    public void createUI(String str) {

        setBounds(80, 80, 450, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));

        title = str;
        // setStatus();
        setTitle("Chat");

        // Create controls
        add(new Label("     Server port     "));
        serverport = new TextField(23);
        add(serverport);
        serverport.setText("9999");

        add(new Label("     Username     "));
        username = new TextField(23);
        add(username);
        username.setText("");        
        
        final List chatList = new List();
        fromserver = new TextArea(10, 50);
        
        connect = new Button("Connect");
        connect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Make sure they wrote the username
                if (username.getText().length() == 0) {
                    fromserver.setText("You must write your username.");
                    return;
                }
                
                username.setEnabled(false);
                serverport.setEnabled(false);
                connect.setEnabled(false);
                
                client = new Client("localhost", serverport.getText(), username.getText());
                                        
                try {
                    for (ChatRoom room : client.getChatService().getRooms()) {
                        chatList.add(room.getName());
                    }
                    
                    for (String participant : client.getChatService().getParticipants(0)) {
                        if (!participant.equals(username.getText()))
                            userList.add(participant);
                    }
                    
                    fromserver.setText(client.getChatService().getHistory(0));
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
                }                               
            }
        });
        add(connect);

        disconnect = new Button("Disconnect");
        disconnect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                username.setEnabled(true);
                serverport.setEnabled(true);
                connect.setEnabled(true);
                userList.removeAll();
                chatList.removeAll();
                client.unregisterChatServiceInterface();
            }
        });
        add(disconnect);

        message = new TextField(30);
        add(message);

        send = new Button("Send message");
        send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (serverport.isEnabled()) {
                    fromserver.setText("You must connect first.");
                    return;
                }
                
                try {
                    client.getChatService().sendMsg(message.getText(), username.getText());
                    message.setText("");
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(send);
        
        add(fromserver);
        fromserver.setEditable(false);

        Label userLabel = new Label("         User list         ");
        add(userLabel);

        Label roomLabel = new Label("         Room list         ");
        add(roomLabel);
        
        add(userList);                
        add(chatList);
        
        setVisible(true);
    }

}
