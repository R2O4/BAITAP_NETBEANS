import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author trann
 */
public class BAI3 {
    public static void main(String[] args) {
        String urlString = "https://zingnews.vn"; // Thay đổi URL nếu cần
        String line;

        try {
            // Tạo đối tượng URL
            URL url = new URL(urlString);

            // Mở kết nối và đọc dữ liệu từ URL
            try (InputStream inputStream = url.openStream();
                 GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream, "UTF-8"))) {
                
                // Đọc từng dòng dữ liệu đã giải nén và in ra
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (IOException e) {
             System.out.println();
        }
    }
}
