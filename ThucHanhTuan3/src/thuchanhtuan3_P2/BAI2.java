/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuchanhtuan3_P2;
import java.net.MalformedURLException;
import java.net.*;
import java.io.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.awt.*;
/**
 *
 * @author trann
 */
public class BAI2 {
    public static void main(String[] args) throws MalformedURLException {
        String s = "https://www.hutech.edu.vn";
        String thisLine;
        try {
            URL u = new URL(s);
            try {
                DataInputStream dis = new DataInputStream(u.openStream());
                BufferedReader br  = new BufferedReader(new InputStreamReader(dis));
                while ((thisLine = br.readLine()) != null){
                    System.out.println(thisLine);
                }
            } catch (IOException e){
                System.err.println(e);
            }
        }catch (MalformedURLException e){
            System.out.println();
        }
    }
}
