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
public abstract class Nhanvien {
    protected String hoTen;
    protected String phong;

    public Nhanvien(String hoTen, String phong) {
        this.hoTen = hoTen;
        this.phong = phong;
}
    
public abstract double tinhLuong();

    @Override
    public String toString() {
        return "Họ tên: " + hoTen + ", Phòng: " + phong;
    }
}
