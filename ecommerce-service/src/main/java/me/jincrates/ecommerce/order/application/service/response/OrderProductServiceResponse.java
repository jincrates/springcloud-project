package me.jincrates.ecommerce.order.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.order.domain.OrderProduct;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProductServiceResponse {

    private Long id;  // 주문상품 ID
    private UUID orderId;
    private Long productId;
    private int orderPrice;  // 주문 가격
    private int quantity;  // 주문 수량

    public OrderProductServiceResponse(Long id, UUID orderId, Long productId, int orderPrice, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public static OrderProductServiceResponse of(OrderProduct entity) {
        return new OrderProductServiceResponse(
                entity.getId(),
                entity.getOrder().getId(),
                entity.getProduct().getId(),
                entity.getOrderPrice(),
                entity.getQuantity());
    }
}
