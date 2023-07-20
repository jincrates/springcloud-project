package me.jincrates.msa.coffeekiosk.unit;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeekiosk.unit.beverage.Americano;
import me.jincrates.msa.coffeekiosk.unit.beverage.Latte;
import me.jincrates.msa.coffeekiosk.unit.order.Order;

@Slf4j
public class CoffeeKioskRunner {

    public static void main(String[] args) {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        coffeeKiosk.add(new Americano());
        log.info(">>> 아메리카노 추가");

        coffeeKiosk.add(new Latte());
        log.info(">>> 라떼 추가");

        int totalPrice = coffeeKiosk.calculateTotalPrice();
        log.info("총 주문가격: {}", totalPrice);

        Order order = coffeeKiosk.createOrder(LocalDateTime.now());
    }
}
