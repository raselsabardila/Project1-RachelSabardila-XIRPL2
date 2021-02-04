package com.pbo.components.factory;

import com.pbo.components.menu.food.kuah.Kuah;

import java.util.ArrayList;

public class KuahFactory {
    static public ArrayList<Kuah> generate() {
        ArrayList data = new ArrayList();
        data.add(new Kuah("Kuah Orisinil", 0, 5));
        data.add(new Kuah("Kuah Internasional", 0, 5));
        data.add(new Kuah("Kuah Spicy", 0, 5));
        data.add(new Kuah("Kuah Soto", 0, 5));

        return data;
    }
}
