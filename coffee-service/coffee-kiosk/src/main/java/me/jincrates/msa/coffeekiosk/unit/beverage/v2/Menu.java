package me.jincrates.msa.coffeekiosk.unit.beverage.v2;

import java.util.List;

public class Menu {
    List<Coffee> list;

    public Menu(List<Coffee> list) {
        this.list = list;
    }

    public void addCoffee(Coffee coffee) {
        list.add(coffee);
    }

    public void addCoffee(List<Coffee> coffees) {
        list.addAll(coffees);
    }

    public List<Coffee> getList() {
        return list;
    }
}
