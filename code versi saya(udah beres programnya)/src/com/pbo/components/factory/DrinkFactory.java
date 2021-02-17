package com.pbo.components.factory;

import com.pbo.components.menu.drink.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkFactory {
    static public ArrayList<Drink> generate() {
        ArrayList<Drink> data = new ArrayList();
        data.add(new Drink("Jus Alpukat", 10000));
        data.add(new Drink("Jus Stoberi", 11000));
        data.add(new Drink("Capucino Coffee", 15000));
        data.add(new Drink("Kopi Tubruk", 14000));

        return data;
    }
}
