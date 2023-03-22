package com.example.loginandregister;

public class NhanVien {
    private String sodienthoai;
    private String ten;
    private String Email;
    private String congviec;
    private String isEmpl;

    public NhanVien(String sodienthoai, String ten, String email, String congviec, String isEmpl) {
        this.sodienthoai = sodienthoai;
        this.ten = ten;
        Email = email;
        this.congviec = congviec;
        this.isEmpl = isEmpl;
    }
    public NhanVien(){
        super();
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

    public String getCongviec() {
        return congviec;
    }

    public void setCongviec(String congviec) {
        this.congviec = congviec;
    }

    public String getIsEmpl() {
        return isEmpl;
    }

    public void setIsEmpl(String isEmpl) {
        this.isEmpl = isEmpl;
    }
}
