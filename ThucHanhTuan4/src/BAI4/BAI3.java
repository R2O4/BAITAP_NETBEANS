package BAI4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class FileTWrite implements Runnable {
    private String fileName;

    public FileTWrite(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Random random = new Random();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(100);
                writer.write(randomNumber + "\n");
            }
            System.out.println("Finished writing to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class BAI3 {
    public static void main(String[] args) {
        FileTWrite writer1 = new FileTWrite("file1.txt");
        FileTWrite writer2 = new FileTWrite("file2.txt");
        FileTWrite writer3 = new FileTWrite("file3.txt");

        Thread thread1 = new Thread(writer1);
        Thread thread2 = new Thread(writer2);
        Thread thread3 = new Thread(writer3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
