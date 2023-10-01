package me.jincrates.ecommerce.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("대기중"),
    PAID("결제완료"),
    APPROVED("승인완료"),
    CANCELLING("취소중"),
    CANCELLED("취소완료");;

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
