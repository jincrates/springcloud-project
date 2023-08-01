package me.jincrates.msa.coffeekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    void containsStockType4() {
        // given
        ProductType givenTYpe1 = ProductType.HANDMADE;
        ProductType givenTYpe2 = ProductType.BOTTLE;
        ProductType givenTYpe3 = ProductType.BAKERY;

        // when
        boolean result1 = ProductType.containsStockType(givenTYpe1);
        boolean result2 = ProductType.containsStockType(givenTYpe2);
        boolean result3 = ProductType.containsStockType(givenTYpe3);

        // then
        assertThat(result1).isFalse();
        assertThat(result2).isTrue();
        assertThat(result3).isTrue();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
    @ParameterizedTest
    void containsStockType5(ProductType productType, boolean expected) {

        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideProductTypeForCheckingStockType() {
        return Stream.of(
            Arguments.of(ProductType.HANDMADE, false),
            Arguments.of(ProductType.BOTTLE, true),
            Arguments.of(ProductType.BAKERY, true)
        );
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @MethodSource("provideProductTypeForCheckingStockType")
    @ParameterizedTest(name = "[{index}] ''{0}''이면, ''{1}''이다.")
    void containsStockType6(ProductType productType, boolean expected) {

        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }
}