package me.jincrates.msa.coffeekiosk.unit;

import me.jincrates.msa.coffeekiosk.unit.beverage.Americano;
import org.junit.jupiter.api.Test;

class CoffeeKioskTest {

    @Test
    void add() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        coffeeKiosk.add(new Americano());

        System.out.println(">>> 담긴 음료 수: " + coffeeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료: " + coffeeKiosk.getBeverages().get(0).getName());
    }
}