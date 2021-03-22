package com.pbo;

import com.pbo.classes.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public DaftarMenu daftarMenu;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String no_transaksi, nama_pemesan, tanggal, no_meja = "", transaksi_lagi = "", pesan_lagi = "", keterangan = "", makan_ditemapt;
        int jumlah_pesanan, no_menu;

        Main app = new Main();
        app.generateDaftarMenu();

        System.out.println("========== TRANSAKSI ==========");

        System.out.print("No transaksi : ");
        no_transaksi = input.next();
        System.out.print("Pemesan : ");
        nama_pemesan = input.next();
        System.out.print("Tanggal : ");
        tanggal = input.next();
        System.out.print("Makan ditempat :  [Y/N]");
        makan_ditemapt = input.next();

        if (makan_ditemapt.equalsIgnoreCase("Y")){
            System.out.print("Nomor Meja : ");
            no_meja = input.next();
        };

        Transaksi transaksi = new Transaksi(no_transaksi, nama_pemesan, tanggal, no_meja);
        System.out.println("========= PESANAN =========");
        int no_kuah;

        do {
            Menu menu_yang_dipilih = app.daftarMenu.pilihMenu();

            jumlah_pesanan = (int) app.cekInputNumber("Jumlah : ");

            Pesanan pesanan = new Pesanan(menu_yang_dipilih, jumlah_pesanan);
            transaksi.tambahPesanan(pesanan);

            if (menu_yang_dipilih.getKategori().equalsIgnoreCase("Ramen")) {
                int jumlah_ramen = jumlah_pesanan;
                do {
                    Menu kuah_yang_dipilih = app.daftarMenu.pilihKuah();

                    System.out.print("Level : [0-5]");
                    String level = input.next();

                    int jumlah_kuah = 0;
                    do {
                        jumlah_kuah = (int) app.cekInputNumber("Jumlah : ");

                        if(jumlah_kuah > jumlah_ramen) {
                            System.out.println("[ERR] jumlah kuah melebihi jumlah ramen");
                        } else {
                            break;
                        }
                    } while (jumlah_kuah > jumlah_ramen);

                    Pesanan pesan_kuah = new Pesanan(kuah_yang_dipilih, jumlah_kuah);
                    pesan_kuah.setKeterangan("Level" + level);

                    transaksi.tambahPesanan(pesan_kuah);

                    jumlah_ramen -= jumlah_kuah;
                } while (jumlah_ramen > 0);
            } else {
                System.out.println("Keterangan [- jika kosong]");
                keterangan = input.next();
            }

            if (!keterangan.equals("-")) {
                pesanan.setKeterangan(keterangan);
            }

            System.out.print("Tambah Pesana Lagi? [Y/N]");
            pesan_lagi = input.next();

        } while(pesan_lagi.equalsIgnoreCase("Y"));

        transaksi.cetakStruk();

        double totalPesanan = transaksi.hitungTotalPesanan();
        System.out.println("===========================");
        System.out.println("total : " + totalPesanan);

        transaksi.setPajak(10);
        double ppn = transaksi.hitungPajak();
        System.out.println("pajak : " + ppn);

        double biaya_service = 0;
        if(makan_ditemapt.equalsIgnoreCase("Y")) {
            transaksi.setBiayaService(5);
            biaya_service = transaksi.hitungBiayaService();
            System.out.println("biaya service" + biaya_service);
        }

        System.out.println("Total " + transaksi.hitungTotalBayar(ppn, biaya_service));

        double kembalian = 0;
        do {
            double uang_bayar = app.cekInputNumber("uang bayar");

            kembalian = transaksi.hitungKembalian(uang_bayar);
            if (kembalian < 0) {
                System.out.println("EE");
            } else {
                System.out.println(" Keambalian" + kembalian);
                break;
            }
        } while (kembalian < 0);

        System.out.println("=========== makasih ===========");
    }

    public void generateDaftarMenu() {
        daftarMenu = new DaftarMenu();
        daftarMenu.tambahMenu(new Ramen("Ramen Seafood", 25000));
        daftarMenu.tambahMenu(new Ramen("Ramen Original", 18000));
        daftarMenu.tambahMenu(new Ramen("Ramen Vegetarian", 22000));
        daftarMenu.tambahMenu(new Ramen("Ramen Karnivor", 28000));
        daftarMenu.tambahMenu(new Kuah("Kuah Orsinil"));
        daftarMenu.tambahMenu(new Kuah("Kuah Internasional"));
        daftarMenu.tambahMenu(new Kuah("Kuah Spicy Lada"));
        daftarMenu.tambahMenu(new Kuah("Kuah Soto Padang"));
        daftarMenu.tambahMenu(new Toping("Crab Stick Bakar", 6000));
        daftarMenu.tambahMenu(new Toping("chicken Katsu", 8000));
        daftarMenu.tambahMenu(new Toping("Gyoza Goreng", 4000));
        daftarMenu.tambahMenu(new Toping("Bakso Goreng", 7000));
        daftarMenu.tambahMenu(new Toping("Enoki Goreng", 5000));
        daftarMenu.tambahMenu(new Minuman("Jus Alpukat SPC", 10000));
        daftarMenu.tambahMenu(new Minuman("Jus Stoberi", 11000));
        daftarMenu.tambahMenu(new Minuman("Capuccino Coffee", 15000));
        daftarMenu.tambahMenu(new Minuman("Vietnam Dripp", 14000));

        daftarMenu.tampilDaftarMenu();
    };

    public double cekInputNumber(String label) {
        try {
            Scanner get_input = new Scanner(System.in);
            System.out.println(label);
            double nilai = get_input.nextDouble();

            return nilai;
        } catch (InputMismatchException err) {
            System.out.println("[ERR] harap masukan angka");
            return cekInputNumber(label);
        }
    }
}
