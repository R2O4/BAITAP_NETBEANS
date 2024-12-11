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
public class Nhanvienhopdong extends Nhanvien{
    private double luongHopDong;
    private String loaiHD;

    public Nhanvienhopdong(String hoTen, String phong, double luongHopDong, String loaiHD) {
        super(hoTen, phong);
        this.luongHopDong = luongHopDong;
        this.loaiHD = loaiHD;
    }
   
    @Override
    public double tinhLuong() {
        return luongHopDong;
    }

    public double getLuongHopDong() {
        return luongHopDong;
    }

    public String getLoaiHD() {
        return loaiHD;
    }

    @Override
    public String toString() {
        return super.toString() + ", Lương hợp đồng: " + luongHopDong + ", Loại hợp đồng: " + loaiHD;
    }
}

