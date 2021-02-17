package com.pbo.classes;

import java.util.ArrayList;

public class Transaksi {
    private String noTransaksi;
    private String namaPemesan;
    private String tanggal;
    private String noMeja;
    private ArrayList<Pesanan> pesanan;
    private double uangBayar;
    private double pajak;
    private double totalBayar;

    public Transaksi(String noTransaksi, String namaPemesan, String tanggal, String noMeja) {
        this.noTransaksi = noTransaksi;
        this.namaPemesan = namaPemesan;
        this.tanggal = tanggal;
        this.noMeja = noMeja;
    }

    public void tambahPesanan(Pesanan pesanan) {};

    public ArrayList<Pesanan> getPesanan() {
        return pesanan;
    }

    public double hitungTotalBayar() {return 0;};

    public double hitungKembalian() {return 0;};

    public void cetakStruk() {};
}
