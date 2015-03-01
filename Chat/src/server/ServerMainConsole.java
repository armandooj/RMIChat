/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

public class ServerMainConsole {

    public static Server server;

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Usage:java HelloClient <server host> <port>");
                return;
            }
            String host = args[0];
            String port = args[1];

            server = new Server(host, port);

        } catch (Exception e) {
            System.err.println("Error on server :" + e);
            e.printStackTrace();
            return;
        }
    }
}
