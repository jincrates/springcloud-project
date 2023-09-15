package me.jincrates.ecommerce.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    RECEIPT("접수"),
    PROGRESS("진행"),
    SUCCESS("완료"),
    REJECT("불가"),
    CANCEL("철회"),
    ;

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
