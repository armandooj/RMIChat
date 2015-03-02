package server.impl;

import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.util.LinkedList;
import service.CallbackClientInterface;
import service.CallbackServerInterface;


public class CallbackServerImpl extends UnicastRemoteObject
        implements CallbackServerInterface {

    private final List<CallbackClientInterface> clientList;

    public CallbackServerImpl() throws RemoteException {
        super();
        clientList = new LinkedList<>();
    }

    @Override
    public String sayHello()
            throws java.rmi.RemoteException {
        return ("hello");
    }

    @Override
    public synchronized void registerForCallback(
            CallbackClientInterface callbackClientObject)
            throws java.rmi.RemoteException {

        if (!(clientList.contains(callbackClientObject))) {
            clientList.add(callbackClientObject);
            System.out.println("Registered new client ");
            doCallbacksForNewUser(callbackClientObject.getName());
        }
    }

    @Override
    public synchronized void unregisterForCallback(
            CallbackClientInterface callbackClientObject)
            throws java.rmi.RemoteException {
        
        if (clientList.remove(callbackClientObject)) {
            System.out.println("Unregistered client ");
            doCallbacksForOldUser(callbackClientObject.getName());
        } else {
            System.out.println("unregister: clientwasn't registered.");
        }
    }

    public synchronized void doCallbacksForNewMsg(String msg, String user) 
            throws java.rmi.RemoteException {
        
        System.out.println("doCallbacksForNewMsg");
        for (CallbackClientInterface nextClient : clientList) {
            nextClient.notifyMeForNewMsg(user + ": " + msg);
        }
    }

    public synchronized void doCallbacksForNewUser(String name)
            throws java.rmi.RemoteException {

        System.out.println("doCallbacksForNewUser");
        for (CallbackClientInterface nextClient : clientList) {
            nextClient.notifyMeForRegisteredUser(name);
        }
    }

    public synchronized void doCallbacksForOldUser(String name)
            throws java.rmi.RemoteException {

        System.out.println("doCallbacksForOldUser");
        for (CallbackClientInterface nextClient : clientList) {
            nextClient.notifyMeForUnregisteredUser(name);
        }
    }

}
