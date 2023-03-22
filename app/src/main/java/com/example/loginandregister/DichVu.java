package com.example.loginandregister;

import java.io.Serializable;

public class DichVu  {
    String anh,ten,mota;

    public DichVu() {
    }

    public DichVu(String anh, String ten, String mota) {
        this.anh = anh;
        this.ten = ten;
        this.mota = mota;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    //    public DichVu(String ten, String anh) {
//        this.ten = ten;
//        this.anh = anh;
//    }
//
//    public String getTen() {
//        return ten;
//    }
//
//    public void setTen(String ten) {
//        this.ten = ten;
//    }
//
//    public String getAnh() {
//        return anh;
//    }
//
//    public void setAnh(String anh) {
//        this.anh = anh;
//    }
}
