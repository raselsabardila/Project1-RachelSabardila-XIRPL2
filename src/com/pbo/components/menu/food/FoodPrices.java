package com.pbo.components.menu.food;

public class FoodPrices extends Food{
    private int price;

    public FoodPrices(String name, int price) {
        super(name);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void update(String name, int price) {
        this.setName(name);
        this.setPrice(price);
    }
}
