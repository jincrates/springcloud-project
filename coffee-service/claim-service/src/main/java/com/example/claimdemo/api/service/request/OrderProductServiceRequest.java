package com.example.claimdemo.api.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductServiceRequest {

    private Long orderProductId;
    private int quantity;

    public OrderProductServiceRequest(Long orderProductId, int quantity) {
        this.orderProductId = orderProductId;
        this.quantity = quantity;
    }
}
