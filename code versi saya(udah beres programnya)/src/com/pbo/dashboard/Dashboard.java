package com.pbo.dashboard;

import com.pbo.components.controller.LoginController;

import java.util.Scanner;

public class Dashboard {
    static public void login() {
        Scanner scan = new Scanner(System.in);

        System.out.println("======================");
        System.out.println("|WELCOME TO DASHBOARD|");
        System.out.println("======================");
        System.out.println("|Login as :          |");
        System.out.println("|[1]. Owner          |");
        System.out.println("|[2]. Pengunjung     |");
        System.out.println("|[3]. Exit           |");
        System.out.println("======================");
        System.out.print("Chose : ");

        int value = scan.nextInt();
        LoginController.login(value);
    }
}
