package com.pbo.classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DaftarMenu {
    private ArrayList<Menu> daftarMenu;

    public DaftarMenu(){
        daftarMenu = new ArrayList<>();
    };

    public void tambahMenu(Menu menu) {
        daftarMenu.add(menu);
    };

    public void getMenuByCategory(String category){
        System.out.println("======== " + category + "========");

        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu m = daftarMenu.get(i);
            if (m.getKategori().equals(category)) {
                System.out.println((i + 1) + ". " + m.getNama_menu() + "\t" + m.getHarga());
            }
        }
    };

    public void tampilDaftarMenu(){
        System.out.println("======== ALDEBARAMEN ========");
        getMenuByCategory("Ramen");
        getMenuByCategory("Kuah");
        getMenuByCategory("Toping");
        getMenuByCategory("Minuman");
    };

    public  Menu pilihMenu() {
        try {
            Scanner input = new Scanner(System.in);

            System.out.print("Nomor Menu : ");
            int no_menu = input.nextInt();

            Menu m = daftarMenu.get(no_menu - 1);

            if(!m.getKategori().equalsIgnoreCase("Kuah")) {
                return m;
            } else {
                System.out.println("[ERR] Pesan ramennya dulu");
                return pilihMenu();
            }
        } catch (InputMismatchException err) {
            System.out.println("[Err] mohon masukan nomor mesu");
            return pilihMenu();
        }
    }

    public Menu pilihKuah() {
        Menu result = new Menu();

        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Kuah [sesuai nomor menu] : ");
            int no_menu = input.nextInt();

            Menu m = daftarMenu.get(no_menu - 1);

            if (m.getKategori().equalsIgnoreCase("Kuah")) {
                result = m;
            } else {
                System.out.println("[ERR] bukan menu kuah");
                return pilihKuah();
            }
        } catch (IndexOutOfBoundsException err) {
            System.out.println("[ERR]");
        } catch (InputMismatchException err) {
            System.out.println("[ERR]");
            return pilihKuah();
        }

        return result;
    }
}
