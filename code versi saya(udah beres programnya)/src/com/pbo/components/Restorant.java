package com.pbo.components;

import com.pbo.components.factory.DrinkFactory;
import com.pbo.components.factory.KuahFactory;
import com.pbo.components.factory.RameFactory;
import com.pbo.components.factory.TopingFactory;
import com.pbo.components.menu.drink.Drink;
import com.pbo.components.menu.food.kuah.Kuah;
import com.pbo.components.menu.food.main.Ramen;
import com.pbo.components.menu.food.toping.Toping;

import java.util.ArrayList;

public class Restorant {
    private String name;
    private static ArrayList<Ramen> ramen = new ArrayList();
    private static ArrayList<Drink> drink = new ArrayList();
    private static ArrayList<Toping> toping = new ArrayList();
    private static ArrayList<Kuah> kuah = new ArrayList();

    public Restorant(String name) {
        this.name = name;
        this.generateFactory();
    }

    private void generateFactory() {
        this.ramen.addAll(RameFactory.generate());
        this.toping.addAll(TopingFactory.generate());
        this.kuah.addAll(KuahFactory.generate());
        this.drink.addAll(DrinkFactory.generate());
    }

    public void menu() {
        Service.menu();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ramen> getRamen() {
        return ramen;
    }

    public ArrayList<Drink> getDrink() {
        return drink;
    }

    public ArrayList<Toping> getToping() {
        return toping;
    }

    public ArrayList<Kuah> getKuah() {
        return kuah;
    }

    public void setRamen(Ramen ramen) {
        Restorant.ramen.add(ramen);
    }

    public void setDrink(Drink drink) {
        Restorant.drink.add(drink);
    }

    public void setToping(Toping toping) {
        Restorant.toping.add(toping);
    }

    public void setKuah(Kuah kuah) {
        Restorant.kuah.add(kuah);
    }

    public boolean deleteFood(int index, int menu) {
        try {
            switch (menu) {
                case 1 :
                    Restorant.ramen.remove(index);
                    break;
                case 2 :
                    Restorant.toping.remove(index);
                    break;
                case 3 :
                    Restorant.kuah.remove(index);
                    break;
                case 4 :
                    Restorant.drink.remove(index);
                    break;
            }

            return true;
        } catch (Exception err) {
            System.out.println(err);
            return false;
        }
    }
}
