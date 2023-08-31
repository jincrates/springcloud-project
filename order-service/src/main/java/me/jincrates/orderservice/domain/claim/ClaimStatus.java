package me.jincrates.orderservice.domain.claim;

import lombok.Getter;

@Getter
public enum ClaimStatus {
    PENDING("결제 대기중"),
    REQUESTED("접수"),
    CANCELED("철회"),
    APPROVED("승인"),
    REJECTED("반려"),
    COLLECTION_PROGRESS("수거중"),
    COLLECTION_COMPLETED("수거완료"),
    REFUND_COMPLETED("환불 완료"),
    REFUND_UNABLE("환불 불가"),
    DELIVERY_PROGRESS("재배송중"),
    DELIVERY_COMPLETED("재배송완료"),
    EXCHANGE_UNABLE("교환 불가");

    private final String description;

    ClaimStatus(String description) {
        this.description = description;
    }
}
