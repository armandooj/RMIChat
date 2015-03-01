/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

public class ClientMainConsole {

    public static Client client;

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Usage:java HelloClient <server host> <port>");
                return;
            }
            String host = args[0];
            String port = args[1];

            client = new Client(host, port);

        } catch (Exception e) {
            System.err.println("Error on client:" + e);
        }
    }
}
