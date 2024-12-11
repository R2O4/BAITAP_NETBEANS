/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhanvien;

/**
 *
 * @author trann
 */
public class Nhanvienbienche extends Nhanvien{
    private double heSoLuong;
    private int soNamCT;

    public Nhanvienbienche(String hoTen, String phong, double heSoLuong, int soNamCT) {
        super(hoTen, phong);
        this.heSoLuong = heSoLuong;
        this.soNamCT = soNamCT;
    }
    
     public double tinhLuong() {
        return heSoLuong * 1000 + soNamCT * 100;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public int getSoNamCT() {
        return soNamCT;
    }

    @Override
    public String toString() {
        return super.toString() + ", Hệ số lương: " + heSoLuong + ", Số năm công tác: " + soNamCT;
    }
}

