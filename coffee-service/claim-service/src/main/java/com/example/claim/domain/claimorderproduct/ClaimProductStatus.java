package com.example.claim.domain.claimorderproduct;

import lombok.Getter;

@Getter
public enum ClaimProductStatus {
    //클래임 상태(1: 접수, 2. 철회, 3: 수거중, 4: 수거완료, 5: 환불완료, 6: 환불불가, 7: 재배송중, 8: 교환완료, 9: 교환불가)
    RECEIPT("접수"),
    WITHDRAWAL("철회"),
    APPROVAL("승인"),
    REJECTION("반려"),
    IN_PICKUP_PROCESS("수거중"),
    PICKUP_COMPLETED("수거완료"),
    REFUND_COMPLETED("환불완료"),
    REFUND_NOT_POSSIBLE("환불불가"),
    REDELIVERY_IN_PROGRESS("재배송중"),
    EXCHANGE_COMPLETED("교환완료"),
    EXCHANGE_NOT_POSSIBLE("교환불가");

    private String description;

    ClaimProductStatus(String description) {
        this.description = description;
    }
}
