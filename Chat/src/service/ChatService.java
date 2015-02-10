/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.*;

/**
 *
 * @author ASUS
 */
public interface ChatService extends Remote {
// A method provided by the
// remore object

    public void join(String name) throws RemoteException;
}
