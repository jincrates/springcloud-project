package me.jincrates.claimservice.api.controller.claim.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClaimProductRequest {

    private Long orderProductId;
    private int quantity;

    @Builder
    private ClaimProductRequest(Long orderProductId, int quantity) {
        this.orderProductId = orderProductId;
        this.quantity = quantity;
    }
}
