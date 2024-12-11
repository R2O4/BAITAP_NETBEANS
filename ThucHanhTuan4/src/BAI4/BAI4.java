package BAI4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FileTReader implements Runnable {
    private String fileName;

    public FileTReader(String fileName) {
        this.fileName = fileName;
    }



    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Đọc file: " + fileName);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Kết thúc đọc file:  " + fileName);
            System.out.println("-----------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class BAI4 {
    public static void main(String[] args) {
        FileTReader reader1 = new FileTReader("file1.txt");
        FileTReader reader2 = new FileTReader("file2.txt");
        FileTReader reader3 = new FileTReader("file3.txt");

        Thread thread1 = new Thread(reader1);
        Thread thread2 = new Thread(reader2);
        Thread thread3 = new Thread(reader3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
