package com.mtha.mygui;

import java.io.Serializable;

public class SanPham implements Serializable {
    String maSP;
    String tenSP;
    double giaSP;
    public SanPham(){}

    public SanPham(String maSP, String tenSP, double giaSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", giaSP=" + giaSP +
                '}';
    }
}
