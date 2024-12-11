
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trann
 */
public class Ceasar {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base + shift) % 26 + base);
            }

            result.append(ch);
        }

        return result.toString();
    }
    
     public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         
         System.out.print("Moi nhap: ");
        String text = sc.nextLine();
         System.err.println("Nhap key: ");
         int key = sc.nextInt();
        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Ban da nhap chu: " + text);
        System.out.println("Ma hoa: " + encrypted);
        System.out.println("Giai Ma: " + decrypted);
    }
}
