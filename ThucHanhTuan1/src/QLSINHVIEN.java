

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class SinhVien implements Serializable {
    private String maSinhVien;
    private String hoTen;
    private String ngaySinh;
    private double diemTrungBinh;

    public SinhVien(String maSinhVien, String hoTen, String ngaySinh, double diemTrungBinh) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diemTrungBinh = diemTrungBinh;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

   @Override
    public String toString() {
        return "SinhVien{" +
                "maSinhVien='" + maSinhVien + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", diemTrungBinh=" + diemTrungBinh +
                '}';
    }
}

class QuanLySinhVien {
    private List<SinhVien> danhSachSinhVien;
    private String tenFile;

    public QuanLySinhVien(String tenFile) {
        this.tenFile = tenFile;
        danhSachSinhVien = new ArrayList<>();
    }

    public void hienThiMenu() {
        System.out.println("Menu:");
        System.out.println("1. Thêm sinh viên");
        System.out.println("2. Hiển thị danh sách sinh viên");
        System.out.println("3. Lưu danh sách sinh viên");
        System.out.println("4. Đọc danh sách sinh viên");
        System.out.println("5. Sắp xếp và hiển thị danh sách sinh viên theo điểm trung bình");
        System.out.println("6. Thoát");
    }

    public void themSinhVien(Scanner scanner) {
        System.out.println("Nhập thông tin sinh viên:");
        System.out.print("Mã sinh viên: ");
        String maSinhVien = scanner.nextLine();
        System.out.print("Họ và tên: ");
        String hoTen = scanner.nextLine();
        System.out.print("Ngày sinh: ");
        String ngaySinh = scanner.nextLine();
        System.out.print("Điểm trung bình: ");
        double diemTrungBinh = scanner.nextDouble();
        scanner.nextLine(); // Đọc dòng trống sau khi nhập điểm trung bình

        SinhVien sinhVien = new SinhVien(maSinhVien, hoTen, ngaySinh, diemTrungBinh);
        danhSachSinhVien.add(sinhVien);

        System.out.println("Thêm sinh viên thành công!");
    }

    public void hienThiDanhSachSinhVien() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng.");
        } else {
            System.out.println("Danh sách sinh viên:");
            for (SinhVien sinhVien : danhSachSinhVien) {
                System.out.println(sinhVien);
            }
        }
    }

    public void luuDanhSachSinhVien() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tenFile))) {
            oos.writeObject(danhSachSinhVien);
            System.out.println("Lưu danh sách sinh viên thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu danh sách sinh viên: " + e.getMessage());
        }
    }

    public void docDanhSachSinhVien() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tenFile))) {
            danhSachSinhVien = (List<SinhVien>) ois.readObject();
            System.out.println("Đọc danh sách sinh viên thành công!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc danh sách sinh viên: " + e.getMessage());
        }
    }
    public void sapXepVaHienThiDanhSachSinhVienTheoDiem() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng.");
        } else {
            danhSachSinhVien.stream()
                    .sorted(Comparator.comparingDouble(SinhVien::getDiemTrungBinh).reversed())
                    .forEach(System.out::println);
        }
    }
}

public class QLSINHVIEN {
    private static final String TEN_FILE = "danhsachsinhvien.dat";

    public static void main(String[] args) {
        QuanLySinhVien quanLySinhVien = new QuanLySinhVien(TEN_FILE);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            quanLySinhVien.hienThiMenu();
            System.out.print("Nhập lựa chọn của bạn: ");
            int luaChon = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng trống sau khi nhập lựa chọn

            switch (luaChon) {
                case 1:
                    quanLySinhVien.themSinhVien(scanner);
                    break;
                case 2:
                    quanLySinhVien.hienThiDanhSachSinhVien();
                    break;
                case 3:
                    quanLySinhVien.luuDanhSachSinhVien();
                    break;
                case 4:
                    quanLySinhVien.docDanhSachSinhVien();
                    break;
                case 5:
                    quanLySinhVien.sapXepVaHienThiDanhSachSinhVienTheoDiem();
                    break;
                    case 6:
                System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        }
    }
}
