package thuchanhtuan6_bai2;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class UDPFTPServer {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/udp_ftp_server";
    private static final String USER = "root";
    private static final String PASS = "";

   
    private static final int MAX_PACKET_SIZE = 65507; 

    public static Connection connectToDatabase() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to MySQL database.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
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
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Server started on port " + port + "...");

            while (true) {
                byte[] receiveData = new byte[MAX_PACKET_SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                new Thread(() -> { // Handle each request in a new thread
                    try {
                        handleRequest(serverSocket, receivePacket);
                    } catch (IOException e) {
                        System.err.println("Error handling request: " + e.getMessage());
                    }
                }).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
    private static void handleRequest(DatagramSocket serverSocket, DatagramPacket receivePacket) throws IOException {
        String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Received: " + request);

        List<String> parts = Arrays.asList(request.split(" "));
        String command = parts.get(0).toUpperCase();

        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        ClientHandler clientHandler = new ClientHandler(serverSocket, clientAddress, clientPort);

        switch (command) {
            case "REGISTER":
                clientHandler.handleREGISTER(parts.subList(1, parts.size()));
                break;
            case "LOGIN":
                clientHandler.handleLOGIN(parts.subList(1, parts.size()));
                break;
            default:
                clientHandler.sendResponse("Unknown command.");
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

class ClientHandler {
    private final DatagramSocket socket;
    private final InetAddress clientAddress;
    private final int clientPort;

    public ClientHandler(DatagramSocket socket, InetAddress clientAddress, int clientPort) {
        this.socket = socket;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
    }
    public void sendResponse(String response) throws IOException {
        byte[] responseData = response.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
        socket.send(responsePacket);
    }
    void handleREGISTER(List<String> arguments) throws IOException {
        if (arguments.size() != 2) {
            sendResponse("Invalid registration format.");
            return;
        }

        String username = arguments.get(0);
        String password = arguments.get(1);

        if (registerUser(username, password)) {
            sendResponse("Registration successful.");
        } else {
            sendResponse("Registration failed. Username might be taken.");
        }
    }

    private boolean registerUser(String username, String password) {
        Connection conn = UDPFTPServer.connectToDatabase();
        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
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

    void handleLOGIN(List<String> arguments) throws IOException {
        if (arguments.size() != 2) {
            sendResponse("Invalid login format.");
            return;
        }

        String username = arguments.get(0);
        String password = arguments.get(1);

        if (authenticate(username, password)) {
            sendResponse("Login successful.");
        } else {
            sendResponse("Login failed. Invalid username or password.");
        }
    }

    private boolean authenticate(String username, String password) {
        Connection conn = UDPFTPServer.connectToDatabase();
        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
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
}