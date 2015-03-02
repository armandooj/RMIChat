package service;

import java.rmi.*;
import java.rmi.server.*;
import client.ClientUI;

public class CallbackClientImpl extends UnicastRemoteObject
        implements CallbackClientInterface {

    private final String name;

    public CallbackClientImpl(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String notifyMeForNewMsg(String message) {
        ClientUI.clientUI.addMsg(message);
        String returnMessage = "msg: [" + message + "]";
        System.out.println(returnMessage);
        return returnMessage;
    }

    @Override
    public String notifyMeForRegisteredUser(String name) throws RemoteException {
        ClientUI.clientUI.addUser(name);
        return name;
    }

    @Override
    public String notifyMeForUnregisteredUser(String name) throws RemoteException {
        ClientUI.clientUI.removeUser(name);
        return name;
    }
}
