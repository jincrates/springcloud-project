package me.jincrates.ecommerce.order.application.service.request;

import lombok.Getter;
import me.jincrates.ecommerce.order.adapter.web.request.OrderCancelRequest;

@Getter
public class OrderCancelServiceRequest {

    private Long productId; // 상품 ID
    private int quantity; // 주문 수량

    public OrderCancelServiceRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public static OrderCancelServiceRequest of(OrderCancelRequest request) {
        return null;
    }
}
