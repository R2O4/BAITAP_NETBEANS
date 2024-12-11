package BAI4;

import java.io.*;
import java.util.Random;

public class BAI5 {
    
    // Class to handle file operations
    static class FileHandler {
        private final String fileName;

        public FileHandler(String fileName) {
            this.fileName = fileName;
        }

        public synchronized void writeToFile() {
            Random random = new Random();
            try (FileWriter writer = new FileWriter(fileName, true)) {
                for (int i = 0; i < 10; i++) {
                    int randomNumber = random.nextInt(100);
                    writer.write(randomNumber + "\n");
                }
                System.out.println("Kết thúc việc ghi: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public synchronized void readFromFile() {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                System.out.println("Đọc dữ liệu: " + fileName);
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("Kết thúc việc đọc dữ liệu: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Runnable for writing to file
    static class FileTWriter implements Runnable {
        private final FileHandler fileHandler;

        public FileTWriter(FileHandler fileHandler) {
            this.fileHandler = fileHandler;
        }

        @Override
        public void run() {
            fileHandler.writeToFile();
        }
    }

    // Runnable for reading from file
    static class FileTReader implements Runnable {
        private final FileHandler fileHandler;

        public FileTReader(FileHandler fileHandler) {
            this.fileHandler = fileHandler;
        }

        @Override
        public void run() {
            fileHandler.readFromFile();
        }
    }

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("bai5.txt");

        FileTWriter writer = new FileTWriter(fileHandler);
        FileTReader reader = new FileTReader(fileHandler);

        Thread writerThread = new Thread(writer);
        Thread readerThread = new Thread(reader);

        writerThread.start();
        readerThread.start();
    }
}
