package me.jincrates.ecommerce.order.application.service.request;

import lombok.Getter;

@Getter
public class OrderCreateServiceRequest {

    private Long productId; // 상품 ID
    private int quantity; // 주문 수량

    public OrderCreateServiceRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
