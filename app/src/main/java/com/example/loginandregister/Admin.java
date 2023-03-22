package com.example.loginandregister;

public class Admin {
    String sdt;
    String ten;
    String matkhau;
    public Admin() {
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public Admin(String ten, String matkhau) {
        this.sdt = sdt;
        this.ten = ten;
        this.matkhau = matkhau;
    }
}
