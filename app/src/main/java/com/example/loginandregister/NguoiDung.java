package com.example.loginandregister;

public class NguoiDung {
    private String sodienthoai;
    private String ten;
    private String Email;
    private String isUser;
    private  String MatKhau;

    public NguoiDung() {
        super();

    }

    public NguoiDung(String sodienthoai, String ten, String email, String isUser, String matKhau) {
        this.sodienthoai = sodienthoai;
        this.ten = ten;
        Email = email;
        this.isUser = isUser;
        MatKhau = matKhau;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
