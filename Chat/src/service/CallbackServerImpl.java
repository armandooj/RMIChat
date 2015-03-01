package service;

import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the remote interface CallbackServerInterface.
 *
 * @author M. L. Liu
 */
public class CallbackServerImpl extends UnicastRemoteObject
        implements CallbackServerInterface {

    private final List<CallbackClientInterface> clientList;

    public CallbackServerImpl() throws RemoteException {
        super();
        clientList = new LinkedList<CallbackClientInterface>();
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
        
        System.out.println("Test");
        
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.add(callbackClientObject);
            System.out.println("Registered new client ");
            doCallbacksForNewUser(callbackClientObject.getName());
        } // end if
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

    public synchronized void doCallbacks(String msg, String user) throws java.rmi.RemoteException {
        // make callback to each registered client
        System.out.println(
                "**************************************\n"
                + "Callbacks initiated ---");
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println("doing " + i + "-th callback\n");
            // convert the vector object to a callback object
            CallbackClientInterface nextClient = clientList.get(i);           
            // invoke the callback method
            nextClient.notifyMeForNewMsg(user + ": " + msg);
       
        }// end for
        System.out.println("********************************\n"
                + "Server completed callbacks ---");
    } // doCallbacks

    public synchronized void doCallbacksForNewUser(String name) throws java.rmi.RemoteException {
        // make callback to each registered client
        System.out.println(
                "**************************************\n"
                + "Callbacks initiated ---");
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println("doing " + i + "-th callback\n");
            // convert the vector object to a callback object
            CallbackClientInterface nextClient = clientList.get(i);
            // invoke the callback method
            nextClient.notifyMeForRegisteredUser(name);
        }// end for
        System.out.println("********************************\n"
                + "Server completed callbacks ---");
    } // doCallbacks

    public synchronized void doCallbacksForOldUser(String name) throws java.rmi.RemoteException {
        // make callback to each registered client
        System.out.println(
                "**************************************\n"
                + "Callbacks initiated ---");
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println("doing " + i + "-th callback\n");
            // convert the vector object to a callback object
            CallbackClientInterface nextClient = clientList.get(i);
            // invoke the callback method
            nextClient.notifyMeForUnregisteredUser(name);
        }// end for
        System.out.println("********************************\n"
                + "Server completed callbacks ---");
    } // doCallbacks

}// end CallbackServerImpl class   
