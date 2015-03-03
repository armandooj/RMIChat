/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import service.CallbackServerInterface;
import java.rmi.registry.*;
import client.impl.CallbackClientImpl;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ChatService;

public class Client {
    
    ChatService chatService;
    CallbackServerInterface callbackServerInterface;
    CallbackClientImpl callbackImp;

    public Client(String host, String port, String username) {
        try {
            Registry registry = LocateRegistry.getRegistry(host);

            String registryURL = "rmi://" + host + ":" + port;

            chatService = (ChatService) registry.lookup(registryURL + "/chat");
            
            callbackServerInterface = (CallbackServerInterface) registry.lookup(registryURL + "/callback");
            callbackImp = new CallbackClientImpl(username);
            callbackServerInterface.registerForCallback(callbackImp);

        } catch (Exception e) {
            System.err.println("Error on client:" + e);
        }
    }
    
    public ChatService getChatService() {
        return chatService;
    }
    
    public void unregisterChatServiceInterface() {
        try {
            callbackServerInterface.unregisterForCallback(callbackImp);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
