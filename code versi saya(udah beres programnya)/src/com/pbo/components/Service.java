package com.pbo.components;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Service {
    public static Restorant restorant = new Restorant("MOG Resto");
    public static User user;
    private static int noPesanan = 0;

    static void menu() {
        System.out.printf("+------------ %s ------------+\n\n", restorant.getName());
        Service.printRamen();
        Service.printToping();
        Service.printKuah();
        Service.printDrink();
    }

    public static void printRamen() {
        System.out.println("------->>> Jenis Ramen <<<-------");
        for (int i = 0; i < Service.restorant.getRamen().size(); i++){
            System.out.printf("%d. %s \t %s \n", i+1, restorant.getRamen().get(i).getName(), Service.toRupiah((int) restorant.getRamen().get(i).getPrice()));
        };
        System.out.println("");
    }

    public static void printToping() {
        System.out.println("------->>> Jenis Toping <<<-------");
        for (int i = 0; i < Service.restorant.getToping().size(); i++){
            System.out.printf("%d. %s \t %s \n", i+1, restorant.getToping().get(i).getName(), Service.toRupiah((int) restorant.getToping().get(i).getPrice()));
        };
        System.out.println("");
    }

    public static void printKuah() {
        System.out.println("------->>> Jenis Kuah <<<-------");
        for (int i = 0; i < Service.restorant.getKuah().size(); i++){
            System.out.printf("%d. %s (Level %s) \n", i+1, restorant.getKuah().get(i).getName(), String.valueOf(restorant.getKuah().get(i).getLevels()));
        };
        System.out.println("");
    }

    public static void printDrink() {
        System.out.println("------->>> Minuman <<<-------");
        for (int i = 0; i < Service.restorant.getDrink().size(); i++){
            System.out.printf("%d. %s \t %s \n", i+1, restorant.getDrink().get(i).getName(), Service.toRupiah((int) restorant.getDrink().get(i).getPrice()));
        };
    }

    public static int input() {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();

        return value;
    }

    public static String toRupiah(int value) {
        double a = Double.valueOf(value);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        return formatRupiah.format(a);
    }

    public static String getNoPesanan(String codeMeja) {
        String result = "A" + String.valueOf(Service.noPesanan += 1);

        return result;
    }
}
