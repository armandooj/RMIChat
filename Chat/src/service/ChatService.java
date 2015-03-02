/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.*;
import java.util.List;
import model.ChatRoom;


public interface ChatService extends Remote {

    public List<ChatRoom> getRooms() throws RemoteException;
    
    public void join(String userName, int roomId) throws RemoteException;
    
    public String getHistory(int roomId) throws RemoteException;
    
    public String[] getParticipants(int roomId) throws RemoteException;
    
    public void leave(int roomId) throws RemoteException;
    
    public void sendMsg(String msg, String user) throws RemoteException;
}
