package com.pbo.components.controller;

import com.pbo.dashboard.Dashboard;
import com.pbo.dashboard.Owner;
import com.pbo.dashboard.Pengunjung;

public class LoginController {
    static public void login(int value) {
        LoginController.validate(value);
    }

    static private void validate(int value) {
        switch (value) {
            case 1 :
                LoginController.loginOwner();
                break;
            case 2 :
                Pengunjung.login();
                break;
            case 3 :
                System.exit(1);
                break;
            default:
                LoginController.clearConsole();
                Dashboard.login();
        }
    }

    private static void loginOwner() {
        Owner.menu();
    }

    static public void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
}
