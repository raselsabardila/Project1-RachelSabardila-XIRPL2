package com.pbo.classes;

public class Pesanan {
    private Menu menu;
    private int jumlah;
    private String Keterangan;

    public Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    };

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public Menu getMenu() {
        return menu;
    };

    public int getJumlah() {
        return jumlah;
    }
}
