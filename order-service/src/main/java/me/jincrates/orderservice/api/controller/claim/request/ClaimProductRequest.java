package me.jincrates.orderservice.api.controller.claim.request;

import lombok.*;

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
