package me.jincrates.claimservice.api.controller.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.claimservice.domain.claim.ClaimReason;
import me.jincrates.claimservice.domain.claim.ClaimType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClaimCreateRequest {

    //주문번호, 주문상품 리스트 {id, 수량}, 클레임유형, 사유, 상세사유
    private Long orderId;
    private ClaimType type;
    private ClaimReason reason;
    private String memo;
    private List<ClaimProductRequest> claimProductRequests;
}
