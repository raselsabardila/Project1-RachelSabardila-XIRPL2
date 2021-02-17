package com.pbo.components.menu.food.kuah;

import com.pbo.components.menu.food.Food;
import com.pbo.components.menu.food.FoodFree;

import java.util.ArrayList;

public class Kuah extends FoodFree {
    private ArrayList levels = new ArrayList();

    public Kuah(String name, int minLevel, int maxLevel) {
        super(name);
        this.setLevel(minLevel, maxLevel);
    }

    private void setLevel(int min, int max) {
        ArrayList data = new ArrayList();

        for (int i = min; i <= max; i++) {
            data.add(i);
        }

        this.levels.addAll(data);
    }

    public String getLevels() {
        String value = this.levels.get(0).toString() + "-" + this.levels.get(this.levels.size() - 1).toString();
        return value;
    }

    public void update(String name, int min, int max) {
        this.setName(name);
        this.setLevel(min, max);
    }
}