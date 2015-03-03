/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import server.impl.ChatServiceImpl;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.LinkedList;
import server.impl.CallbackServerImpl;
import service.ChatService;

import java.util.List;
import model.ChatRoom;

/**
 *
 * @author ASUS
 */
public class Server {

    public List<ChatRoom> rooms;
    public List<String> participants;
    public CallbackServerImpl csi;
    public String history = "";

    public Server(String host, String port) {
        try {

            // Create a Hello remote object
            ChatServiceImpl h = new ChatServiceImpl();
            ChatService h_stub = (ChatService) UnicastRemoteObject.exportObject(h, 0);

            csi = new CallbackServerImpl();
            //CallbackServerImpl csi_stub = (CallbackServerImpl) UnicastRemoteObject.exportObject(csi, 0);

            // Register the remote object in RMI
            // registry with a given identifier
            Registry registry = LocateRegistry.getRegistry();
            String registryURL = "rmi://" + host + ":" + port + "/";
            registry.bind(registryURL + "chat", h_stub);
            registry.bind(registryURL + "callback", csi);

            rooms = new LinkedList<>();
            rooms.add(new ChatRoom(1, "Fun"));
            rooms.add(new ChatRoom(2, "Science"));
            rooms.add(new ChatRoom(3, "Serious"));
            
            participants = new LinkedList<>();

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Error on server :" + e);
            return;
        }
    }
}
