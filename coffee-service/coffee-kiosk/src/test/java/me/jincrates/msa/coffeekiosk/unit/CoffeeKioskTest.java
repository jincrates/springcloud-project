package me.jincrates.msa.coffeekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;

import me.jincrates.msa.coffeekiosk.unit.beverage.Americano;
import me.jincrates.msa.coffeekiosk.unit.beverage.Latte;
import org.junit.jupiter.api.Test;

class CoffeeKioskTest {

    @Test
    void add_manual_test() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        coffeeKiosk.add(new Americano());

        System.out.println(">>> 담긴 음료 수: " + coffeeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료: " + coffeeKiosk.getBeverages().get(0).getName());
    }

    @Test
    void add() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        coffeeKiosk.add(new Americano());

        assertThat(coffeeKiosk.getBeverages()).hasSize(1);
        assertThat(coffeeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void remove() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();

        coffeeKiosk.add(americano);
        assertThat(coffeeKiosk.getBeverages()).hasSize(1);

        coffeeKiosk.remove(americano);
        assertThat(coffeeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        coffeeKiosk.add(americano);
        coffeeKiosk.add(latte);
        assertThat(coffeeKiosk.getBeverages()).hasSize(2);

        coffeeKiosk.clear();
        assertThat(coffeeKiosk.getBeverages()).isEmpty();
    }
}