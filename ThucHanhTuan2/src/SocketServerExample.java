/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
/**
 *
 * @author trann
 */
public class SocketServerExample {
    public static void main(String[] args){
        try {
            ServerSocket serverSocke = new ServerSocket(1234);
            System.out.println("Server da khoi dong va dang lang nghe cong 1234 ...");
            
            Socket clientSocket = serverSocke.accept();
            System.out.println("Client da ket noi ...");
            
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            
            String clientMessage = in.readLine();
            System.out.println("Client: " + clientMessage);
            out.print("Xin Chao, Client!");
            
            clientSocket.close();
            serverSocke.close();
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
