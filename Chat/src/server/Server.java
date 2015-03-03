/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import server.impl.ChatServiceImpl;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.LinkedList;
import server.impl.CallbackServerImpl;
import service.ChatService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        System.out.println(new File("history.txt").getAbsolutePath());
        // Read the persistent history
        history = readFromFile("../../history.txt");
        
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
    
    private static String readFromFile(String filename) {
        String everything;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                try {
                    line = br.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            everything = sb.toString();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return everything;
    }
    
    public void writeToFile(String fileName, String data) {
        try {
           PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
           out.println(data);
           out.close();
       } catch (IOException e) {
           //exception handling left as an exercise for the reader
       }
    }
}
