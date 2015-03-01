package service;

import java.rmi.*;
import java.rmi.server.*;

/**
 * This class implements the remote interface CallbackClientInterface.
 *
 * @author M. L. Liu
 */
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
        String returnMessage = "Call back received: " + message;
        System.out.println(returnMessage);
        return returnMessage;
    }

    @Override
    public String notifyMeForRegisteredUser(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String notifyMeForUnregisteredUser(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}// end CallbackClientImpl class   
