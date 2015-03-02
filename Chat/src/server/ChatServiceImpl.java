/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import service.ChatService;

import java.rmi.*;
import java.util.List;
import model.ChatRoom;

public class ChatServiceImpl implements ChatService {

    @Override
    public List<ChatRoom> getRooms() throws RemoteException {
        return ServerMainConsole.server.rooms;
    }

    @Override
    public void join(String userName, int roomId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leave(int roomId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendMsg(String msg, String user) throws RemoteException {
        ServerMainConsole.server.csi.doCallbacksForNewMsg(msg, user);
    }

    @Override
    public String getHistory(int roomId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getParticipants(int roomId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
