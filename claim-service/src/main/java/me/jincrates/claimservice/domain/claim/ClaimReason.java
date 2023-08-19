package me.jincrates.claimservice.domain.claim;

import lombok.Getter;

import java.util.List;

/**
 * 클레임 사유(1: 단순변심, 2: 파손 및 불량, 3: 주문 실수, 4: 오배송 및 지연)
 */
@Getter
public enum ClaimReason {
    //구매자 귀책
    CHANGE_MIND("단순변심"),
    ORDERING_MISTAKE("주문 실수"),
    //판매자 귀책
    PRODUCT_DEFECTS("파손 및 불량"),
    DELIVERY_ISSUES("오배송 및 지연");

    private final String description;

    ClaimReason(String description) {
        this.description = description;
    }

    /**
     * 구매자 귀책여부 확인
     *
     * @param reason 클레임 사유
     * @return
     */
    public boolean isBuyerResponsibility(ClaimReason reason) {
        return List.of(CHANGE_MIND, ORDERING_MISTAKE).contains(reason);
    }

    /**
     * 판매자 귀책여부 확인
     *
     * @param reason 클레임 사유
     * @return
     */
    public boolean isSellerResponsibility(ClaimReason reason) {
        return List.of(PRODUCT_DEFECTS, DELIVERY_ISSUES).contains(reason);
    }

    /**
     * 파손 및 불량
     *
     * @return
     */
    public boolean isProductDefects() {
        return this == PRODUCT_DEFECTS;
    }
}
