package thuchanhtuan5_lab6_bai3;

import java.net.*;
import javax.swing.JOptionPane;

public class Main {
    private static final int PORT = 1234; // Port Configuration

    public static void main(String[] args) {
        Chat app = new Chat(); // Assuming `Chat` is a GUI class
        app.setVisible(true);

        DatagramSocket socket = null;
        String strContent = "";
        
        try {
            socket = new DatagramSocket(PORT);
            byte[] buffer = new byte[1024];
            boolean ktFinish = false;
            DatagramPacket receivePacket;

            while (!ktFinish) {
                receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);
                String stReceive = new String(receivePacket.getData(), 0, receivePacket.getLength());

                strContent = app.getContentChat(); // Assuming `getContentChat` is a method of `Chat`
                strContent += "Nhận: " + stReceive + "\n";
                app.setContentChat(strContent); // Assuming `setContentChat` is a method of `Chat`

                if ("end.".equals(stReceive)) {
                    ktFinish = true;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
