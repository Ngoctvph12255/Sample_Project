/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author NgocTV
 */
public class ChuyenDe {
    String maCD;
    String tenCD;
    double hocPhi;
    int thoiLuong;
    String hinhAnh;
    String moTa;

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    //lấy tên hiển thị combobox
    @Override
    public String toString(){
        return this.tenCD;
    }
//    @Override
//    public boolean equals(Object obj){
//        ChuyenDe other = (ChuyenDe) obj;
//        //lấy mã so sánh 2 chuyên đề
//        return other.getMaCD().equals(this.getMaCD());
//    }
}
