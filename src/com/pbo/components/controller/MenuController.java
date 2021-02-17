package com.pbo.components.controller;

import com.pbo.components.Restorant;
import com.pbo.components.Service;
import com.pbo.components.menu.drink.Drink;
import com.pbo.components.menu.food.kuah.Kuah;
import com.pbo.components.menu.food.main.Ramen;
import com.pbo.components.menu.food.toping.Toping;
import com.pbo.dashboard.Owner;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {
    static public void create(int menu) {
        Scanner scanner = new Scanner(System.in);
        String[] data = {"ramen", "toping", "kuah", "drink"};
        ArrayList result = new ArrayList();

        if (menu != 3) {
            result = (MenuController.createPrices(scanner, data, menu));
        } else {
            result = (MenuController.createFree(scanner, data, menu));
        }

        System.out.println(result.get(0));

        switch (menu) {
            case 1 :
                Service.restorant.setRamen(new Ramen(result.get(0).toString(), Integer.valueOf((Integer) result.get(1))));
                break;
            case 2 :
                Service.restorant.setToping(new Toping(result.get(0).toString(), Integer.valueOf((Integer) result.get(1))));
                break;
            case 3 :
                Service.restorant.setKuah(new Kuah(result.get(0).toString(), (Integer) result.get(1), (Integer) result.get(2)));
                break;
            case 4 :
                Service.restorant.setDrink(new Drink(result.get(0).toString(), Integer.valueOf((Integer) result.get(1))));
                break;
        }

        LoginController.clearConsole();

        rePrintMenu(menu);
        System.out.println("berhasil menambahkan " + result.get(0));
    }

    private static ArrayList createPrices(Scanner scanner, String[] data, int menu) {
        System.out.print("Input name " + data[menu - 1] + " : ");
        String name = scanner.nextLine();

        System.out.print("Input Price : ");
        int price = scanner.nextInt();
        price = MenuController.checkValueMin(price);

        ArrayList result = new ArrayList();
        result.add(name);
        result.add(price);

        return result;
    }

    public static int checkValueMin(int value) {
        int result = value;

        if (result < 0) {
            result *= -1;
        }

        return result;
    }

    private static ArrayList createFree(Scanner scanner, String[] data, int menu) {
        System.out.print("Input name " + data[menu - 1] + " : ");
        String name = scanner.nextLine();
        System.out.println("");

        System.out.print("Input min level : ");
        int min = scanner.nextInt();
        min = MenuController.checkValueMin(min);

        System.out.printf("Input max level(min: %d) : ", min + 1);
        int max = scanner.nextInt();
        max = MenuController.checkValueMin(max);

        if (!(min < max)) {
            LoginController.clearConsole();
            MenuController.create(menu);
        }

        ArrayList result = new ArrayList();
        result.add(name);
        result.add(min);
        result.add(max);

        return result;
    }

    private static void rePrintMenu(int menu) {
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

    public static void delete(int index, int menu, int action) {
        FoodPricesCrud crud = new FoodPricesCrud();

        LoginController.clearConsole();
        boolean status = Service.restorant.deleteFood(index - 1, menu);
        LoginController.clearConsole();

        if (status) {
            MenuController.rePrintMenu(menu);
            crud.checkFinal(menu, action);
        } else {
            Owner.validateAction(menu, action);
        }
    }

    public static void update(int index, int menu, int action) {
        Scanner scanner = new Scanner(System.in);
        FoodPricesCrud crud = new FoodPricesCrud();
        int price = 0;
        int min = 0;
        int max = 0;

        index = index - 1;

        System.out.print("Input new name : ");
        String name = scanner.nextLine();

        if (menu == 3) {
            System.out.print("Input new min level : ");
            min = scanner.nextInt();
            int minPlus = MenuController.checkValueMin(min);

            System.out.printf("Input new max level(min : %d) : ", minPlus + 1);
            max = scanner.nextInt();
            int maxPlus = MenuController.checkValueMin(max);

            Service.restorant.getKuah().get(index).update(name, minPlus, maxPlus);
        } else {
            System.out.print("Input new price : ");
            price = scanner.nextInt();

            int pricePlus = MenuController.checkValueMin(price);

            try {
                switch (menu) {
                    case 1 :
                        Service.restorant.getRamen().get(index).update(name, pricePlus);
                        break;
                    case 2 :
                        Service.restorant.getToping().get(index).update(name, pricePlus);
                        break;
                    case 4 :
                        Service.restorant.getDrink().get(index).update(name, pricePlus);
                        break;
                }
            } catch (Exception err) {
                Owner.validateAction(menu, action);
            }
        }

        LoginController.clearConsole();
        crud.printMenu(menu);
        crud.checkFinal(menu, action);
    }
}
