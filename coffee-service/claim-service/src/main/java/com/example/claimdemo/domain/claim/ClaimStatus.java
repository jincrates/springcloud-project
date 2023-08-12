package com.example.claimdemo.domain.claim;

import lombok.Getter;

@Getter
public enum ClaimStatus {
    //클래임 상태(1: 접수, 2. 철회, 3: 승인, 4: 반려, 5: 수거중, 6: 수거완료, 7: 환불완료, 8: 환불불가, 9: 재배송중, 10: 교환완료, 11: 교환불가)
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

    ClaimStatus(String description) {
        this.description = description;
    }
}
