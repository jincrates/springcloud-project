package me.jincrates.claimservice.api.controller.request;

import lombok.*;
import me.jincrates.claimservice.domain.claim.ClaimReason;
import me.jincrates.claimservice.domain.claim.ClaimType;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClaimCreateRequest {

    //주문번호, 주문상품 리스트 {id, 수량}, 클레임유형, 사유, 상세사유
    private Long orderId;
    private ClaimType type;
    private ClaimReason reason;
    private String memo;
    private List<ClaimProductRequest> claimProductRequests;

    @Builder
    private ClaimCreateRequest(Long orderId, ClaimType type, ClaimReason reason, String memo, List<ClaimProductRequest> claimProductRequests) {
        this.orderId = orderId;
        this.type = type;
        this.reason = reason;
        this.memo = memo;
        this.claimProductRequests = claimProductRequests;
    }
}
