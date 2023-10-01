package me.jincrates.ecommerce.product.domain;

import lombok.Getter;

@Getter
public enum StockType {
    IN("입고"),
    OUT("출고");

    private final String description;

    StockType(String description) {
        this.description = description;
    }
}
