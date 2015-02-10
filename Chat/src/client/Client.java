/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.*;
import java.rmi.registry.*;
import service.ChatService;

/**
 *
 * @author ASUS
 */
public class Client {

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Usage:java HelloClient <server host>");
                return;
            }
            String host = args[0];
// Get remote object reference
            Registry registry
                    = LocateRegistry.getRegistry(host);
            ChatService h = (ChatService) registry.lookup("Hello1");
// Remote method invocation
            h.join("jad");
            //System.out.println(res);
        } catch (Exception e) {
            System.err.println("Error on client:" + e);
        }
    }
}
