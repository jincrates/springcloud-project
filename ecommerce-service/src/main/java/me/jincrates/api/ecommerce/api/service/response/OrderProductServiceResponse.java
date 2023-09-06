package me.jincrates.api.ecommerce.api.service.response;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.api.ecommerce.domain.order.OrderProduct;

@Getter
public class OrderProductServiceResponse {

    private Long id;  // 주문상품 ID
    private UUID orderId;
    private Long productId;
    private int orderPrice;  // 주문 가격
    private int quantity;  // 주문 수량

    @Builder(access = AccessLevel.PRIVATE)
    private OrderProductServiceResponse(Long id, UUID orderId, Long productId, int orderPrice,
        int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public static OrderProductServiceResponse of(OrderProduct orderProduct) {
        return OrderProductServiceResponse.builder()
            .id(orderProduct.getId())
            .orderId(orderProduct.getOrder().getId())
            .productId(orderProduct.getProduct().getId())
            .orderPrice(orderProduct.getOrderPrice())
            .quantity(orderProduct.getQuantity())
            .build();
    }
}
