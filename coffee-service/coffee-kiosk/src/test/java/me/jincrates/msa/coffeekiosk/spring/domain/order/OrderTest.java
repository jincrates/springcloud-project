package me.jincrates.msa.coffeekiosk.spring.domain.order;

import static me.jincrates.msa.coffeekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static me.jincrates.msa.coffeekiosk.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import me.jincrates.msa.coffeekiosk.spring.domain.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    void calculateTotalPrice() {
        // given
        List<Product> product = List.of(
            createProduct("001", 1000),
            createProduct("002", 2000)
        );

        // when
        Order order = Order.create(product, LocalDateTime.now());

        // then
        assertThat(order.getTotalPrice()).isEqualTo(3000);
    }

    @Test
    @DisplayName("주문 생성 시 주문 상태는 INIT이다.")
    void init() {
        // given
        List<Product> product = List.of(
            createProduct("001", 1000),
            createProduct("002", 2000)
        );

        // when
        Order order = Order.create(product, LocalDateTime.now());

        // then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.INIT);
    }

    @Test
    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    void registeredAt() {
        // given
        LocalDateTime registeredAt = LocalDateTime.now();
        List<Product> product = List.of(
            createProduct("001", 1000),
            createProduct("002", 2000)
        );

        // when
        Order order = Order.create(product, registeredAt);

        // then
        assertThat(order.getRegisteredAt()).isEqualTo(registeredAt);
    }

    private Product createProduct(String productNumber, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(HANDMADE)
            .price(price)
            .sellingStatus(SELLING)
            .name("메뉴 이름")
            .build();
    }
}