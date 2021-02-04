package com.pbo.dashboard;

import com.pbo.components.Service;
import com.pbo.components.User;
import com.pbo.components.controller.LoginController;
import com.pbo.components.controller.Meja;
import com.pbo.components.controller.PesananController;

import java.util.Scanner;

public class Pengunjung {
    public static void login() {
        Scanner scanner = new Scanner(System.in);

        LoginController.clearConsole();

        System.out.printf(">>>>>>> Selemat Datang di %s <<<<<<<\n", Service.restorant.getName());
        System.out.print("Input name user : ");
        String name = scanner.nextLine();

        Service.user = new User(name);

        LoginController.clearConsole();
        Pengunjung.checkLayanan();
    }

    private static void checkLayanan() {
        System.out.printf("Halo %s silahkan pilih layanan kami :)\n\n", Service.user.getName());

        System.out.println(">>>> Layanan");
        System.out.printf("[1]. Makan ditempat(%s)\n", Meja.cekLength());
        System.out.print("[2]. Bawa pulang\n");
        System.out.print("[3]. Exit\n");
        System.out.print("Chose layanan : ");
        int value = Service.input();

        LoginController.clearConsole();
        switch (value) {
            case 1 :
                Meja.printMeja();

                System.out.println("\n>>>> Next Action");
                System.out.print("[1]. Chose meja\n");
                System.out.print("[2]. kembali\n");
                System.out.print("Chose action : ");
                int action = Service.input();

                LoginController.clearConsole();
                switch (action) {
                    case 1 :
                        Pengunjung.choseMeja();
                        break;
                    case 2 :
                        Pengunjung.checkLayanan();
                        break;
                    default:
                }

                break;
            case 2 :
                PesananController.pesanMenu("");
                break;
            case 3 :
                Dashboard.login();
                break;
            default:
                Pengunjung.checkLayanan();
        }
    }

    private static void choseMeja() {
        Scanner scanner = new Scanner(System.in);

        Meja.printMeja();
        System.out.print("Chose meja id : ");
        String value = scanner.nextLine();

        boolean isempty = Meja.checkEmpty(value);

        LoginController.clearConsole();
        if (isempty) {
            Pengunjung.choseMeja();
        } else {
            LoginController.clearConsole();
            PesananController.pesanMenu(value);
        }
    }
}
