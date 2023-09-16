package me.jincrates.ecommerce.order.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.order.domain.Order;
import me.jincrates.ecommerce.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceResponse {

    private UUID id;  // 주문 ID
    private LocalDateTime orderedAt;  // 주문일자
    private OrderStatus orderStatus; // 주문 상태
    private List<OrderProductServiceResponse> orderProducts = new ArrayList<>();  // 주문 상품 list

    public OrderServiceResponse(UUID id, LocalDateTime orderedAt, OrderStatus orderStatus,
                                List<OrderProductServiceResponse> orderProducts) {
        this.id = id;
        this.orderedAt = orderedAt;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }

    public static OrderServiceResponse of(Order entity) {
        return new OrderServiceResponse(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getOrderStatus(),
                entity.getOrderProducts().stream()
                        .map(OrderProductServiceResponse::of)
                        .collect(Collectors.toList())
        );
    }
}
