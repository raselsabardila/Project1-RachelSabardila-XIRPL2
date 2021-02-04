package com.pbo.classes;

public class Pesanan {
    private Menu menu;
    private int jumlah;
    private String Keterangan;

    public Pesanan(Menu menu, int jumlah) {};

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }
}
