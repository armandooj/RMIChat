/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import service.CallbackServerInterface;
import java.rmi.registry.*;
import client.impl.CallbackClientImpl;
import service.ChatService;

public class Client {
    
    ChatService chatService;

    public Client(String host, String port, String username) {
        try {
            Registry registry = LocateRegistry.getRegistry(host);

            String registryURL = "rmi://" + host + ":" + port;

            chatService = (ChatService) registry.lookup(registryURL + "/chat");
            CallbackServerInterface callbackServerInterface
                    = (CallbackServerInterface) registry.lookup(registryURL + "/callback");

            callbackServerInterface.registerForCallback(new CallbackClientImpl(username));

        } catch (Exception e) {
            System.err.println("Error on client:" + e);
        }
    }
    
    public ChatService getChatService() {
        return chatService;
    }
}
