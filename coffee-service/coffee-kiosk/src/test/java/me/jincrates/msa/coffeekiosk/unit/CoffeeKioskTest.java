package me.jincrates.msa.coffeekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import me.jincrates.msa.coffeekiosk.unit.beverage.Americano;
import me.jincrates.msa.coffeekiosk.unit.beverage.Latte;
import me.jincrates.msa.coffeekiosk.unit.order.Order;
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

    @Test
    void createOrder() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();
        coffeeKiosk.add(americano);

        Order order = coffeeKiosk.createOrder();

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderWithCurrentTime() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();
        coffeeKiosk.add(americano);

        Order order = coffeeKiosk.createOrder(LocalDateTime.of(2023, 7, 20, 10, 0));

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderOutsideOpenTime() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();
        coffeeKiosk.add(americano);

        assertThatThrownBy(() -> coffeeKiosk.createOrder(LocalDateTime.of(2023, 7, 20, 9, 59)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
    }
}














