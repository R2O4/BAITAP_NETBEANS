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
public class SocketClientExample {
    public static void main (String[] args) {
    try{
        String serverAddress = "localhost";
        int serverPort = 1234;
        
        Socket socket = new Socket(serverAddress, serverPort);
        
        InputStream inputStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream, true);
        
        out.print("Xin chao, Server!");
        
        String serverResponse = in.readLine();
            System.out.println("Server: "+serverResponse);
        
        socket.close(); 
    } catch (IOException e) 
    {
        e.printStackTrace();
    }
    }
}
