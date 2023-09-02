package me.jincrates.api.claims.api.controller.request;

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
