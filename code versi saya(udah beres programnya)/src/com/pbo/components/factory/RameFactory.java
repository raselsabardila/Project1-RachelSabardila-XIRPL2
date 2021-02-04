package com.pbo.components.factory;

import com.pbo.components.menu.food.main.Ramen;

import java.util.ArrayList;
import java.util.List;

public class RameFactory {
    static public ArrayList<Ramen> generate() {
        ArrayList<Ramen> data = new ArrayList();
        data.add(new Ramen("Ramen Seafood", 25000));
        data.add(new Ramen("Ramen Ori", 18000));
        data.add(new Ramen("Ramen Vegetarian", 22000));
        data.add(new Ramen("Ramen Karnivor", 28000));

        return data;
    }
}
