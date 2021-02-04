package com.pbo.dashboard;

import com.pbo.components.Service;
import com.pbo.components.controller.FoodPricesCrud;
import com.pbo.components.controller.LoginController;
import com.pbo.components.menu.food.main.Ramen;

import java.util.ArrayList;

public class Owner {
    public static void menu() {
        LoginController.clearConsole();

        System.out.println("+---------- CRUD DATA ----------+");
        Owner.printSubMenu();
    }

    private static void printSubMenu() {
        String[] data = {"Jenis Ramen", "Toping Ramen", "Kuah Ramen", "Minuman"};

        for (int i = 0; i < data.length; i++) {
            System.out.printf("[%d]. %s\n", i+1, data[i]);
        }
        System.out.printf("[%d]. Exit\n", data.length + 1);

        System.out.print("Chose menu : ");
        int menu = Service.input();

        LoginController.clearConsole();
        System.out.println("Chose action : ");
        System.out.println("   [1]. Create");
        System.out.println("   [2]. Read");
        System.out.println("   [3]. Update");
        System.out.println("   [4]. Delete");

        if (menu > 0 && menu < 6) {
            if (menu == 5) {
                LoginController.clearConsole();
                Dashboard.login();
            } else {
                System.out.print("Chose action : ");
                int action = Service.input();

                Owner.validateAction(menu, action);
            }
        } else {
            LoginController.clearConsole();
            Owner.printSubMenu();
        }
    }

    public static void validateAction(int menu, int action) {
        FoodPricesCrud crud = new FoodPricesCrud();

        switch (action) {
            case 1 :
                crud.create(menu, action);
                break;
            case 2 :
                crud.read(menu, action);
                break;
            case 3 :
                crud.update(menu, action);
                break;
            case 4 :
                crud.delete(menu, action);
                break;
            default:
                LoginController.clearConsole();
                Owner.printSubMenu();
                break;
        }
    }
}
