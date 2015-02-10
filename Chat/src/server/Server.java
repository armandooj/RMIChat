/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.server.*;
import java.rmi.registry.*;
import service.ChatService;

/**
 *
 * @author ASUS
 */
public class Server {

    public static void main(String[] args) {
        try {
            // Create a Hello remote object
            ChatServiceImpl h = new ChatServiceImpl();
            ChatService h_stub = (ChatService) UnicastRemoteObject.exportObject(h, 0);
            // Register the remote object in RMI
            // registry with a given identifier
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello1", h_stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Error on server :" + e);
            e.printStackTrace();
            return;
        }
    }
}
