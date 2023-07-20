package me.jincrates.msa.coffeekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import me.jincrates.msa.coffeekiosk.unit.beverage.Americano;
import me.jincrates.msa.coffeekiosk.unit.beverage.Latte;
import org.junit.jupiter.api.Test;

class CoffeeKioskTest {

    @Test
    void add_manual_test() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        coffeeKiosk.add(new Americano(), 1);

        System.out.println(">>> 담긴 음료 수: " + coffeeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료: " + coffeeKiosk.getBeverages().get(0).getName());
    }

    @Test
    void add() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        coffeeKiosk.add(new Americano(), 1);

        assertThat(coffeeKiosk.getBeverages()).hasSize(1);
        assertThat(coffeeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void addSeveralBeverages() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();

        coffeeKiosk.add(americano, 2);

        assertThat(coffeeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(coffeeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }

    @Test
    void addZeroBeverages() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();

        // 경계값인 0을 가지고 해피 케이스(addSeveralBeverages)와 예외 케이스(addZeroBeverages)를 검증하는 단위테스트를 작성하는 것이 중요하다.
        assertThatThrownBy(() -> coffeeKiosk.add(americano, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
    }


    @Test
    void remove() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();

        coffeeKiosk.add(americano, 1);
        assertThat(coffeeKiosk.getBeverages()).hasSize(1);

        coffeeKiosk.remove(americano);
        assertThat(coffeeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        coffeeKiosk.add(americano, 1);
        coffeeKiosk.add(latte, 1);
        assertThat(coffeeKiosk.getBeverages()).hasSize(2);

        coffeeKiosk.clear();
        assertThat(coffeeKiosk.getBeverages()).isEmpty();
    }
}