package me.jincrates.msa.coffeekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import me.jincrates.msa.coffeekiosk.unit.beverage.Americano;
import me.jincrates.msa.coffeekiosk.unit.beverage.Latte;
import me.jincrates.msa.coffeekiosk.unit.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * [ DisplayName을 섬세하게 ]
 * <p>
 * 1. 명사의 나열보다는 문장으로 작성하기("~테스트"는 지양하기)
 * <p>
 * - A이면 B이다.
 * <p>
 * - A이면 B가 아니고 C다.
 * <p>
 * 2. 테스트 행위에 대한 결과까지 기술하기
 * <p>
 * - 음료를 1개 추가할 수 있다. (X)
 * <p>
 * - 음료를 1개 추가하면 주문 목록에 담긴다. (O)
 * <p>
 * 3. 도메인 용어를 사용하여 한층 추상화된 내용을 담기
 * <p>
 * - 특정 시간 이전에 주문을 생성하면 실패한다. (X)
 * <p>
 * - 영업 시작 시간 이전에는 주문을 생성할 수 없다. (O)
 * <p>
 * 4. 테스트의 현상을 중점으로 기술하지 말 것
 * <p>
 * - ~하면 성공/실패한다. (X)
 * <p>
 * - ~하면 ~~하다. (O)
 */
class CoffeeKioskTest {

    @Test
    void add_manual_test() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        coffeeKiosk.add(new Americano());

        System.out.println(">>> 담긴 음료 수: " + coffeeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료: " + coffeeKiosk.getBeverages().get(0).getName());
    }

    @Test
    //@DisplayName("음료 1개 추가 테스트")
    @DisplayName("음료 1개 추가하면 주문 목록에 담긴다.")
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
    void calculateTotalPrice() {
        CoffeeKiosk coffeeKiosk = new CoffeeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        coffeeKiosk.add(americano);
        coffeeKiosk.add(latte);

        int totalPrice = coffeeKiosk.calculateTotalPrice();

        assertThat(totalPrice).isEqualTo(8500);
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














