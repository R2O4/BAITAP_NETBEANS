package thuhanhtuan6_bai1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class FTPServer {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ftp_server";
    private static final String USER = "root";
    private static final String PASS = ""; 

    public static Connection connectToDatabase() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Ket noi MySQL database.");
        } catch (SQLException e) {
            System.out.println("Ket noi that bai: " + e.getMessage());
        }
        return conn;
    }

    public static void createUsersTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255) NOT NULL, "
                + "password VARCHAR(255) NOT NULL"
                + ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Users table is ready.");
        } catch (SQLException e) {
            System.out.println("Error creating users table: " + e.getMessage());
        }
    }

    public static void startServer() {
        int port = 2121; 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected...");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection conn = connectToDatabase();
        if (conn != null) {
            createUsersTable(conn);
        }
        startServer();
    }
}

class ClientHandler extends Thread {
    private final Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private String directory  = "E:\\baitap";

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
//        this.directory = "E:\\baitap"; 
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);


            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received: " + request);
                List<String> parts = Arrays.asList(request.split(" "));
                String command = parts.get(0).toUpperCase();
                List<String> arguments = parts.subList(1, parts.size());

                switch (command) {
                    case "USER":
                        handleUSER(arguments);
                        break;
                    case "PASS":
                        handlePASS(arguments);
                        break;
                    case "LIST":
                        handleLIST();
                        break;
                    case "RETR":
                        handleRETR(arguments);
                        break;
                    case "STOR":
                        handleSTOR(arguments);
                        break;
                    case "REGISTER":
                        handleREGISTER(arguments);
                        break;
                    case "LOGIN":
                        handleLOGIN(arguments);
                        break;
                    case "QUIT":
                        handleQUIT();
                        break;
                    default:
                        out.println(" Unknown command.");
                }
            }
        } catch (IOException e) {
            System.out.println("Client handler error: " + e.getMessage());
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing streams: " + e.getMessage());
            }
        }
    }

    private void handleREGISTER(List<String> arguments) {
        if (arguments.size() != 2) {
            out.println("Invalid registration format.");
            return;
        }
    
        String username = arguments.get(0);
        String password = arguments.get(1);
    
        if (registerUser(username, password)) {
            out.println("Registration successful.");
        } else {
            out.println("Registration failed. Username might be taken.");
        }
    }
    private boolean registerUser(String username, String password) {
        Connection conn = FTPServer.connectToDatabase();
        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?)")) {
                stmt.setString(1, username);
                stmt.setString(2, password); 
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.err.println("Error registering user: " + e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
        return false;
    }
    
    

    private void handleLOGIN(List<String> arguments) {
        if (arguments.size() != 2) {
            out.println("Invalid login format.");
            return;
        }
    
        String username = arguments.get(0);
        String password = arguments.get(1);
    
        if (authenticate(username, password)) {
            this.username = username; 
            out.println("Login successful");
        } else {
            out.println("Login failed. Invalid username or password.");
        }
    }

    private void handleUSER(List<String> arguments) {
        if (arguments.size() != 1) {
            out.println(" Syntax error in command.");
            return;
        }
        this.username = arguments.get(0);
        out.println(" User name okay, need password.");
    }

    private void handlePASS(List<String> arguments) {
        if (arguments.size() != 1) {
            out.println(" Syntax error in command.");
            return;
        }

        String password = arguments.get(0);

        if (authenticate(username, password)) {
            out.println(" User logged in, proceed.");
        } else {
            out.println(" Not logged in.");
        }
    }

    private boolean authenticate(String username, String password) {
        Connection conn = FTPServer.connectToDatabase();
        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?")) {
                stmt.setString(1, username);
                stmt.setString(2, password); 
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return true; 
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error authenticating user: " + e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
        return false; 
    }
    private void handleLIST() throws IOException {
    File dir = new File(directory);
    if (!dir.isDirectory()) {
        out.println(" Not a directory.");
        return;
    }

    StringBuilder fileList = new StringBuilder();
    for (File file : dir.listFiles()) {
        fileList.append(file.getName()).append(file.isDirectory() ? "/" : "").append("\n");
    }
    out.println(fileList.toString());
}


    private void handleRETR(List<String> arguments) throws IOException {
        if (arguments.size() != 1) {
            out.println(" Syntax error in command.");
            return;
        }
        String filename = arguments.get(0);
        File file = new File(directory, filename);
        if (!file.exists()) {
            out.println(" File not found.");
            return;
        }
        out.println("SIZE " + file.length()); 
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                clientSocket.getOutputStream().write(buffer, 0, bytesRead);
            }
            out.println(" File transfer successful.");
        }
    }

    private void handleSTOR(List<String> arguments) throws IOException {
        if (arguments.size() != 2) { 
            out.println(" Syntax error in command.");
            return;
        }

        String filename = arguments.get(0);
        long fileSize = Long.parseLong(arguments.get(1));
        File file = new File(directory, filename);
        out.println(" Opening data connection for file upload.");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesRead = 0; 

            InputStream is = clientSocket.getInputStream();
            while (totalBytesRead < fileSize && (bytesRead = is.read(buffer)) != -1) {
                totalBytesRead += bytesRead;
                fos.write(buffer, 0, bytesRead);
            }

            out.println(" File upload successful."); 
        } catch (IOException e) {
            out.println(" File upload failed."); 
        }
    }
    

    private void handleQUIT() {
        out.println(" Goodbye.");
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}