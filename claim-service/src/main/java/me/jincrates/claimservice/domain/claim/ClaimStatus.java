package me.jincrates.claimservice.domain.claim;

import lombok.Getter;

@Getter
public enum ClaimStatus {
    // 1: 반품접수, 2: 반품승인, 3: 반품반려, 4: 반품수거중, 5: 반품수거완료, 6: 환불완료, 7: 환불불가, 8: 반품철회,
    // 9. 교환접수, 10. 교환승인, 11. 교환반려, 12. 교환수거중, 13. 교환수거완료, 14. 재배송중, 15: 재배송 완료, 16. 교환완료, 17: 교환불가, 18: 교환철회
    RECEIPT("접수"),
    WITHDRAWAL("철회"),
    APPROVAL("승인"),
    REJECTION("반려"),
    COLLECTION_PROGRESS("수거중"),
    COLLECTION_COMPLETED("수거완료"),
    REFUND_COMPLETED("환불완료"),
    REFUND_NOT_POSSIBLE("환불불가"),
    DELIVERY_PROGRESS("재배송중"),
    DELIVERY_COMPLETED("재배송완료"),
    EXCHANGE_COMPLETED("교환완료"),
    EXCHANGE_NOT_POSSIBLE("교환불가");

    private final String description;

    ClaimStatus(String description) {
        this.description = description;
    }
}
