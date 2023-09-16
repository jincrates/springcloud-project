package me.jincrates.ecommerce.order.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.order.domain.Order;
import me.jincrates.ecommerce.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Schema(description = "주문 response")
public record OrderResponse(

        @Schema(description = "주문 ID", example = "1")
        UUID id,
        @Schema(description = "주문일자")
        LocalDateTime orderedAt,
        @Schema(description = "주문 상태", example = "REQUEST")
        OrderStatus orderStatus,
        @Schema(description = "주문 상품 목록")
        List<OrderProductResponse> orderProducts
) {

    public OrderResponse {
        orderProducts = orderProducts == null ? new ArrayList<>() : orderProducts;
    }

    public static OrderResponse of(Order entity) {
        return new OrderResponse(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getOrderStatus(),
                entity.getOrderProducts().stream()
                        .map(OrderProductResponse::of)
                        .toList()
        );
    }
}
