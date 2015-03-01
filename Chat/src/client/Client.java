/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import service.CallbackServerInterface;
import java.rmi.registry.*;
import service.CallbackClientImpl;
import service.ChatService;

public class Client {

    public Client(String host, String port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host);

            String registryURL = "rmi://" + host + ":" + port;

            ChatService h = (ChatService) registry.lookup(registryURL + "/chat");
            CallbackServerInterface callbackServerInterface
                    = (CallbackServerInterface) registry.lookup(registryURL + "/callback");

            callbackServerInterface.registerForCallback(new CallbackClientImpl("jad"));

            // h.join("jad", 1);
            //System.out.println(res);
        } catch (Exception e) {
            System.err.println("Error on client:" + e);
        }
    }
}
