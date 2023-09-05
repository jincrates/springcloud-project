package me.jincrates.api.ecommerce.api.service.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductServiceResponse {

    private Long id;  // 주문상품 ID
    private UUID orderId;
    private Long productId;
    private int orderPrice;  // 주문 가격
    private int quantity;  // 주문 수량

    @Builder
    private OrderProductServiceResponse(Long id, UUID orderId, Long productId, int orderPrice,
        int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }
}
