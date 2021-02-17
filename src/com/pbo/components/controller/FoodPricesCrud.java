package com.pbo.components.controller;

import com.pbo.components.Service;
import com.pbo.dashboard.Dashboard;
import com.pbo.dashboard.Owner;

public class FoodPricesCrud implements CRUD{

    @Override
    public void create(int menu, int action) {
        LoginController.clearConsole();

        MenuController.create(menu);

        this.checkFinal(menu, action);
    }

    @Override
    public void read(int menu, int action) {
        LoginController.clearConsole();

        this.printMenu(menu);

        this.checkFinal(menu, action);
    }

    @Override
    public void update(int menu, int action) {
        LoginController.clearConsole();

        this.printMenu(menu);
        System.out.print("Input no item update : ");
        int value = Service.input();

        MenuController.update(value, menu, action);
    }

    @Override
    public void delete(int menu, int action) {
        LoginController.clearConsole();

        this.printMenu(menu);
        System.out.print("Input no item delete : ");
        int value = Service.input();

        MenuController.delete(value, menu, action);
    }

    public void checkFinal(int menu, int action) {
        System.out.println("====> Action");
        System.out.println("    [1]. Kembali");
        System.out.println("    [2]. Exit");
        System.out.print("Chose : ");
        int value = Service.input();

        if (value == 1 || value == 2) {
            switch (value) {
                case 1 :
                    Owner.menu();
                    break;
                case 2 :
                    LoginController.clearConsole();
                    Dashboard.login();
                    break;
            }
        } else {
            Owner.validateAction(menu, action);
        }
    }

    public void printMenu(int menu) {
        switch (menu) {
            case 1 :
                Service.printRamen();
                break;
            case 2 :
                Service.printToping();
                break;
            case 3 :
                Service.printKuah();
                break;
            case 4 :
                Service.printDrink();
                break;
        }
    }
}
