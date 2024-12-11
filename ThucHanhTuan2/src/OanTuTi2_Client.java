import java.io.*;
import java.net.*;

public class OanTuTi2_Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String serverMessage;
        while ((serverMessage = in.readLine()) != null) {
            System.out.println(serverMessage);
            if (serverMessage.contains("Enter your choice")) {
                String userInput = stdIn.readLine();
                out.println(userInput);
            }
        }

        socket.close();
    }
}
