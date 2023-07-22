package me.jincrates.msa.coffeekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTypeTest {

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    void containsStockType() {
        // given
        ProductType givenTYpe = ProductType.HANDMADE;

        // when
        boolean result = ProductType.containsStockType(givenTYpe);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    void containsStockType2() {
        // given
        ProductType givenTYpe = ProductType.BOTTLE;

        // when
        boolean result = ProductType.containsStockType(givenTYpe);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    void containsStockType3() {
        // given
        ProductType givenTYpe = ProductType.BAKERY;

        // when
        boolean result = ProductType.containsStockType(givenTYpe);

        // then
        assertThat(result).isTrue();
    }
}