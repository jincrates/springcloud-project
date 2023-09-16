package me.jincrates.ecommerce.order.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.order.domain.OrderProduct;

import java.util.UUID;

@Schema(description = "주문 상품 response")
public record OrderProductResponse(
        @Schema(description = "주문 상품 ID", example = "1")
        Long id,
        @Schema(description = "주문 ID")
        UUID orderId,
        @Schema(description = "상품 ID", example = "1")
        Long productId,
        @Schema(description = "주문 가격", example = "10000")
        Integer orderPrice,
        @Schema(description = "주문 수량", example = "1")
        Integer quantity
) {

    public static OrderProductResponse of(OrderProduct entity) {
        return new OrderProductResponse(
                entity.getId(),
                entity.getOrder().getId(),
                entity.getProduct().getId(),
                entity.getOrderPrice(),
                entity.getQuantity());
    }
}
