package thuchanhtuan1;

import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

public class TempServer2 {
    private static final int PORT = 9999;
    private static HashMap<String, Integer> temperatureData = new HashMap<>();

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            byte[] receiveData = new byte[1024];

            // Display the server IP address
            displayServerIPAddress();

            System.out.println("UDP Server is listening...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                String[] data = message.split(":");
                if (data.length == 3 && data[0].equals("Temperature")) {
                    String location = data[1];
                    int temperature = Integer.parseInt(data[2]);
                    String clientAddress = receivePacket.getAddress().getHostAddress();
                    temperatureData.put(location + "(" + clientAddress + ")", temperature);

                    // Check temperature threshold
                    checkTemperatureThreshold(location, clientAddress, temperature);
                }

                // Display temperature data from all connected IoT devices
                displayTemperatureData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkTemperatureThreshold(String location, String clientAddress, int temperature) {
        if (temperature < 0 || temperature > 35) {
            String alertMessage = "Temperature alert at location " + location + " (" + clientAddress + "): "
                    + temperature + " °C at " + getCurrentTime();
            System.out.println(alertMessage);
            saveAlertToLogFile(alertMessage);
        }
    }

    private static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    private static void displayServerIPAddress() {
        try {
            // Get the local IP address of the server
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    // Filter and find the IP address of the local interface (not loopback)
                    if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                        String serverIP = inetAddress.getHostAddress();
                        System.out.println("Server IP address: " + serverIP);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private static void displayTemperatureData() {
        System.out.println("Temperature data:");
        for (String location : temperatureData.keySet()) {
            int temperature = temperatureData.get(location);
            System.out.println("Location " + location + ": " + temperature + " °C");
        }
    }

    private static void saveAlertToLogFile(String alertMessage) {
        try {
            FileWriter fileWriter = new FileWriter("alerts_log2.txt", true);
            fileWriter.write(alertMessage + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
