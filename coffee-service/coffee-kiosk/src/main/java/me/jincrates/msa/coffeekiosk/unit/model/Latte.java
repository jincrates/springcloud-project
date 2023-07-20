package me.jincrates.msa.coffeekiosk.unit.model;

import me.jincrates.msa.coffeekiosk.unit.model.Beverage;

public class Latte implements Beverage {

    @Override
    public String getName() {
        return "라떼";
    }

    @Override
    public int getPrice() {
        return 4500;
    }
}
