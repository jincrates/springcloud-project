package me.jincrates.ecommerce.product.domain;

import java.util.List;
import lombok.Getter;

@Getter
public enum ProductSellingStatus {
    SELLING("판매중"),
    HOLD("판매 보류"),
    STOP_SELLING("판매중지"),
    ;

    private final String description;

    ProductSellingStatus(String description) {
        this.description = description;
    }

    public static List<ProductSellingStatus> forDisplay() {
        return List.of(SELLING, HOLD);
    }
}
