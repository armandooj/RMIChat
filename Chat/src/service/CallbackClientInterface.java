package service;

import java.rmi.*;

/**
 * This is a remote interface for illustrating RMI 
 * client callback.
 * @author M. L. Liu
 */

public interface CallbackClientInterface 
  extends java.rmi.Remote{
    
    public String getName();

    public String notifyMeForNewMsg(String message) 
      throws java.rmi.RemoteException;
    
    public String notifyMeForRegisteredUser(String name) 
      throws java.rmi.RemoteException;
    
    public String notifyMeForUnregisteredUser(String name) 
      throws java.rmi.RemoteException;

} // end interface
