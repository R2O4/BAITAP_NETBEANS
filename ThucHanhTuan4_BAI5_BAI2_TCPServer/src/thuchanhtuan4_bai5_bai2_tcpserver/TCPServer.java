
package thuchanhtuan4_bai5_bai2_tcpserver;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    static final int PORT = 1234;
    private ServerSocket server = null;

    public TCPServer() {
        try {
            server = new ServerSocket(PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void action() {
        Socket socket = null;
        int i = 0;
        System.out.println("Server listening...");

        try {
            while ((socket = server.accept()) != null) {
                new ServerThread(socket, "Client#" +i);
                System.out.printf("Thread for Client #%d generating...\n", i++);
            }
        } catch (Exception e) {
                e.printStackTrace();
        } 
    }

    public static void main(String[] args) {
        new TCPServer().action();
    }
}   
