package me.jincrates.claimservice.api.controller.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClaimProductRequest {

    private Long orderProductId;
    private int quantity;

    @Builder
    private ClaimProductRequest(Long orderProductId, int quantity) {
        this.orderProductId = orderProductId;
        this.quantity = quantity;
    }
}
