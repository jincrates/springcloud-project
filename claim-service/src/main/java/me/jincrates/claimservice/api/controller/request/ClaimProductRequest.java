package me.jincrates.claimservice.api.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClaimProductRequest {

    private Long orderProductId;
    private int quantity;

    private ClaimProductRequest(Long orderProductId, int quantity) {
        this.orderProductId = orderProductId;
        this.quantity = quantity;
    }
}
