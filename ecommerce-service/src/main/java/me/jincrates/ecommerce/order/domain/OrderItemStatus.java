package me.jincrates.ecommerce.order.domain;

import lombok.Getter;

@Getter
public enum OrderItemStatus {
    ORDERED("주문처리"),
    CANCELLED("주문취소"),
    ;

    private final String description;

    OrderItemStatus(String description) {
        this.description = description;
    }
}
