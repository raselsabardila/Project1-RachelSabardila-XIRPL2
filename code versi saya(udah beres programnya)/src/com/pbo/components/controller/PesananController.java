package com.pbo.components.controller;

import com.pbo.components.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class PesananController {
    private static int[] toping = new int[Service.restorant.getToping().size()];
    private static int[] minuman = new int[Service.restorant.getDrink().size()];
    private static ArrayList<int[]> ramenPack = new ArrayList();
    private static ArrayList<ArrayList<int[]>> kuahPack = new ArrayList();

    public static void pesanMenu(String noMeja) {
        PesananController.pesanRamen();
        PesananController.pesanOther(1);
        PesananController.pesanOther(2);

        PesananController.countAll(noMeja);
    }

    private static void countAll(String noMeja) {
        int totalToping = PesananController.getTotalToping();
        int totalMinuman = PesananController.getTotalMinuman();
        int ramen = PesananController.getTotalRamen();
        int subTotal = PesananController.getSubTotal(totalToping, totalMinuman, ramen);
        int pajak = PesananController.getPajak(subTotal);

        int service = 0;
        if(noMeja.length() != 0) {
            service = PesananController.getServiceCost(pajak);
        }

        int total = PesananController.getTotal(subTotal, pajak, service);
        int kembalian = PesananController.bayar(total);

        if (kembalian >= 0) {
            LoginController.clearConsole();
            PesananController.cetakStruk(noMeja, subTotal, pajak, total, service, kembalian);
        } else {
            PesananController.countAll(noMeja);
        }
    }

    private static void pesanRamen() {
        Service.restorant.menu();
        boolean again = false;

        do {
            int[] arrRamen = new int[2];
            ArrayList<int[]> kuahRamen = new ArrayList();

            System.out.print("Pilih ramen : ");
            int ramen = MenuController.checkValueMin(Service.input());
            System.out.print("Jumlah ramen : ");
            int jumlah = MenuController.checkValueMin(Service.input());

            arrRamen[0] = ramen - 1;
            arrRamen[1] = jumlah;

            for (int i = 0; i < jumlah; i++){
                System.out.print("Pilih kuah : ");
                int kuah = MenuController.checkValueMin(Service.input());

                System.out.printf("Pilih level kuah(level %s) : ", Service.restorant.getKuah().get(kuah - 1).getLevels());
                int level = MenuController.checkValueMin(Service.input());

                kuahRamen.add(new int[]{kuah-1, level});
            }

            PesananController.ramenPack.add(arrRamen);
            PesananController.kuahPack.add(kuahRamen);

            again = PesananController.tanya();
        } while (again);
    }

    private static boolean pesanOther(int key) {
        boolean again = false;

        PesananController.showMenu();

        do {
            System.out.printf("Pilih %s : ", (key == 1) ? "toping" : "drink");
            int name = MenuController.checkValueMin(Service.input());

            if (key == 1) {
                if (name < 1 || name > Service.restorant.getToping().size()) {
                    PesananController.pesanOther(1);
                } else {
                    System.out.print("jumlah pesanan : ");
                    int jumlah = MenuController.checkValueMin(Service.input());
                    PesananController.toping[name - 1] += jumlah;

                    again = PesananController.tanya();
                }
            } else {
                if (name < 1 || name > Service.restorant.getDrink().size()) {
                    PesananController.pesanOther(2);
                } else {
                    System.out.print("jumlah pesanan : ");
                    int jumlah = MenuController.checkValueMin(Service.input());
                    PesananController.minuman[name - 1] += jumlah;

                    again = PesananController.tanya();
                }
            }
        } while (again);

        LoginController.clearConsole();
        return again;
    }


    private static boolean tanya() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Pesan lagi?(y/n) :");
        String result = scanner.nextLine();

        return result.equalsIgnoreCase("y");
    }

    private static void showMenu() {
        Service.restorant.menu();
        System.out.println("\n>>>> Pilh menu ");
    }

    private static void cetakStruk(String noMeja, int subTotal, int pajak, int total, int service, int kembalian) {
        System.out.printf("+---------- %s -----------+\n", Service.restorant.getName());
        System.out.printf("No Pesanana   : %s\n", Service.getNoPesanan(noMeja));
        System.out.printf("Pemesan       : %s\n", Service.user.getName());
        System.out.printf("Tanggal       : %s\n", PesananController.getDate());
        System.out.printf("No Meja       : %s\n", (noMeja.length() != 0) ? noMeja : "-");
        System.out.println("---------------------------------");

        for (int i = 0; i < PesananController.ramenPack.size(); i++) {
            System.out.printf("%d %s            %s\n", PesananController.ramenPack.get(i)[1], Service.restorant.getRamen().get(PesananController.ramenPack.get(i)[0]).getName(), Service.toRupiah((int) (Service.restorant.getRamen().get(PesananController.ramenPack.get(i)[0]).getPrice() * PesananController.ramenPack.get(i)[1])));
            for (int k = 0; k < PesananController.kuahPack.get(i).size(); k++) {
                System.out.printf("  %s lvl %d\n", Service.restorant.getKuah().get(PesananController.kuahPack.get(i).get(k)[0]).getName(), PesananController.kuahPack.get(i).get(k)[1]);
            }
        }

        for (int i = 0; i < PesananController.toping.length; i++) {
            if (PesananController.toping[i] > 0) {
                System.out.printf("%d %s            %s\n", PesananController.toping[i], Service.restorant.getToping().get(i).getName(), Service.toRupiah((int) (Service.restorant.getToping().get(i).getPrice() * PesananController.toping[i])));
            }
        }

        for (int i = 0; i < PesananController.minuman.length; i++) {
            if (PesananController.minuman[i] > 0) {
                System.out.printf("%d %s            %s\n", PesananController.minuman[i], Service.restorant.getDrink().get(i).getName(), Service.toRupiah((int) (Service.restorant.getDrink().get(i).getPrice() * PesananController.minuman[i])));
            }
        }

        System.out.println("---------------------------------");

        System.out.printf("Sub total           %s\n", Service.toRupiah(subTotal));
        System.out.printf("Pajak 10 persen                %s\n", Service.toRupiah(pajak));
        System.out.printf("Biaya service               %s\n", Service.toRupiah(service));

        System.out.println("---------------------------------");

        System.out.printf("Total            %s\n", Service.toRupiah(total));
        System.out.printf("Uang Bayar           %s\n", Service.toRupiah(total + kembalian));

        System.out.println("---------------------------------");
        System.out.printf("Kembalian            %s", Service.toRupiah(kembalian));
    }

    private static String getDate() {
        DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
        Date d = new Date();
        String result = df.format(d);

        return result;
    }

    private static int getSubTotal(int toping, int minuman, int ramen) {
        return toping + minuman + ramen;
    }

    private static int getPajak(int subTotal) {
        return subTotal / 10;
    }

    private static int getServiceCost(int pajak) {
        return pajak / 2;
    }

    private static int getTotal(int subTotal, int pajak, int service) {
        return subTotal + service + pajak;
    }

    private static int bayar(int total) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Total : %s\n", Service.toRupiah(total));
        System.out.print("input uang bayar : ");
        int bayar = MenuController.checkValueMin(scanner.nextInt());

        return bayar - total;
    }

    private static int getTotalToping() {
        int totalToping = 0;
        for (int i = 0; i < PesananController.toping.length; i++) {
            if (PesananController.toping[i] > 0) {
                totalToping += Service.restorant.getToping().get(i).getPrice() * PesananController.toping[i];
            }
        }

        return totalToping;
    }

    private static int getTotalMinuman() {
        int totalMinuman = 0;
        for (int i = 0; i < PesananController.minuman.length; i++) {
            if (PesananController.minuman[i] > 0) {
                totalMinuman += Service.restorant.getDrink().get(i).getPrice() * PesananController.minuman[i];
            }
        }

        return totalMinuman;
    }

    private static int getTotalRamen() {
        int totalRamen = 0;
        for (int i = 0; i < PesananController.ramenPack.size(); i++) {
            totalRamen += Service.restorant.getRamen().get(PesananController.ramenPack.get(i)[0]).getPrice() * PesananController.ramenPack.get(i)[1];
        }

        return totalRamen;
    }
}