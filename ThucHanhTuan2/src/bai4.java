import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class bai4 {

    public static void main(String[] args) {
        // Nhập tên tập tin từ người dùng
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Nhập tên tập tin đầu vào: ");
        try {
            String inputFileName = reader.readLine();
            processFile(inputFileName);
        } catch (IOException e) {
            System.err.println("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    private static void processFile(String inputFileName) {
        // Đọc nội dung từ tập tin đầu vào
        List<Integer> numbers = new ArrayList<>();
        try (FileReader fr = new FileReader(inputFileName);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.err.println("Dòng không phải là số nguyên: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Có lỗi xảy ra khi đọc tập tin: " + e.getMessage());
            return;
        }

        // Tách số nguyên thành các đoạn với kích thước tối đa là 100 phần tử
        List<int[]> segments = new ArrayList<>();
        int segmentSize = 100;
        for (int i = 0; i < numbers.size(); i += segmentSize) {
            int end = Math.min(i + segmentSize, numbers.size());
            int[] segment = new int[end - i];
            for (int j = i; j < end; j++) {
                segment[j - i] = numbers.get(j);
            }
            segments.add(segment);
        } 

        // Ghi các đoạn vào tập tin mới "Mang.txt"
        try (FileWriter fw = new FileWriter("Mang.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (int[] segment : segments) {
                for (int number : segment) {
                    bw.write(number + " ");
                }
                bw.newLine(); // Mỗi đoạn được ghi trên một dòng mới
            }

            System.out.println("Đã ghi các mảng vào Mang.txt");

        } catch (IOException e) {
            System.err.println("Có lỗi xảy ra khi ghi tập tin: " + e.getMessage());
        }
    }
}
