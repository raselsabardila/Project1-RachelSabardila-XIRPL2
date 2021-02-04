package com.pbo.components.controller;

import com.pbo.components.Service;

import java.util.ArrayList;

public class Meja {
    private static String[][][] meja = {
            {{"1A", "Ujang sekop"},{"2A", "Kosong"},{"3A", "Kak rose"}},
            {{"1B", "Kosong"},{"2B", "Kosong"},{"2B", "Kosong"}},
            {{"1C", "Kosong"},{"2C", "gopal"},{"3C", "Kosong"}}
    };

    public static void printMeja() {
        LoginController.clearConsole();

        System.out.println(">>>> List Meja");
        for (String[][] row : Meja.meja) {
            for (String[] col : row) {
                System.out.printf("%s : %s | ", col[0], col[1]);
            }
            System.out.println("");
        }
    }

    public static String cekLength() {
        int value = 0;

        for (String[][] row : Meja.meja) {
            for (String[] col : row) {
                if (col[1].equals("Kosong")) {
                    value += 1;
                }
            }
        }

        return (value == 0) ? "Tidak ada meja kosong" : "Meja Kosong : " + value;
    }

    public static boolean checkEmpty(String value) {
        boolean isempty = true;

        for (String[][] row : Meja.meja) {
            for (String[] col : row) {
                if (col[0].equalsIgnoreCase(value)) {
                    if (col[1].equals("Kosong")){
                        isempty = false;
                        col[1] = Service.user.getName();
                    }
                }
            }
        }

        return isempty;
    }
}
