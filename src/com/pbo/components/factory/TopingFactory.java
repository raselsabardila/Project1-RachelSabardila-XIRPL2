package com.pbo.components.factory;

import com.pbo.components.menu.food.toping.Toping;

import java.util.ArrayList;

public class TopingFactory {
    static public ArrayList<Toping> generate() {
        ArrayList data = new ArrayList();
        data.add(new Toping("Crabstick", 6000));
        data.add(new Toping("Chicken Katsu", 8000));
        data.add(new Toping("Nori", 4000));
        data.add(new Toping("Meatball", 7000));

        return data;
    }
}
