package com.example.claim.api.service.request;

import com.example.claim.domain.claim.ClaimReason;
import com.example.claim.domain.claim.ClaimType;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClaimCreateServiceRequest {

    //주문번호, 주문상품 리스트 {id, 수량}, 클레임유형, 사유, 상세사유
    private Long orderId;

    private List<OrderProductServiceRequest> orderProducts;

    private ClaimType type;

    private ClaimReason reason;

    private String memo;

    @Builder
    private ClaimCreateServiceRequest(Long orderId, List<OrderProductServiceRequest> orderProducts,
        ClaimType type, ClaimReason reason, String memo) {
        this.orderId = orderId;
        this.orderProducts = orderProducts;
        this.type = type;
        this.reason = reason;
        this.memo = memo;
    }
}
