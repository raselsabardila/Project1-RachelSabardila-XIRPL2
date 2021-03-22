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

    private double biayaService = 0;

    public Transaksi(String noTransaksi, String namaPemesan, String tanggal, String noMeja) {
        this.noTransaksi = noTransaksi;
        this.namaPemesan = namaPemesan;
        this.tanggal = tanggal;
        this.noMeja = noMeja;

        pesanan = new ArrayList<>();
    }

    public void tambahPesanan(Pesanan pesanan) {
        this.pesanan.add(pesanan);
    };

//    public ArrayList<Pesanan> getPesanan() {
//        return pesanan;
//    }

    public  ArrayList<Pesanan> getSemuaPesanan() {
        return pesanan;
    }

    public void setBiayaService(double service) {
        this.biayaService = service;
    }

    public void setPajak(double pajak) {
        this.pajak = pajak;
    }

    public double hitungTotalPesanan() {
        for (int i = 0; i < pesanan.size(); i++) {
            Pesanan psn = pesanan.get(i);
            double harga = psn.getMenu().getHarga();
            totalBayar += (harga * psn.getJumlah());
        }

        return  totalBayar;
    }

    public double hitungPajak() {
        return totalBayar * pajak;
    }

    public double hitungBiayaService() {
        return totalBayar * biayaService;
    }

    public double hitungTotalBayar(double pajak, double service) {
        totalBayar = totalBayar + pajak + service;
        return totalBayar;
    };

    public double hitungKembalian(double uangBayar) {
        return uangBayar - totalBayar;
    };

    public void cetakStruk() {
        System.out.println("======= ALDEBARAMEN =======");
        System.out.println("no transaksi : " + noTransaksi);
        System.out.println("no pemesan : " + namaPemesan);
        System.out.println("tanggal : " + tanggal);

        if (noMeja.equals("")) {
            noMeja = "Take Away";
        }

        System.out.println("Meja" + noMeja);
        System.out.println("====================");
        for (int i = 0; i < pesanan.size(); i++) {
            Pesanan psn = pesanan.get(i);
            Menu m = psn.getMenu();
            String pesanan = psn.getJumlah() + " " + m.getNama_menu() + "\t" + (m.getHarga() * psn.getJumlah());

            if(m.getKategori().equalsIgnoreCase("Kuah")) {
                pesanan = " " + pesanan;
            }

            System.out.println(pesanan);
        }
    };
}
