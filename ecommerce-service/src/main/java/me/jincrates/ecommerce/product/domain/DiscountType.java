package me.jincrates.ecommerce.product.domain;

import lombok.Getter;

@Getter
public enum DiscountType {
    FIXED("정액"),
    RATE("정률");

    private final String description;

    DiscountType(String description) {
        this.description = description;
    }
}

