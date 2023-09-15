package me.jincrates.ecommerce.orders.application.service.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.ecommerce.orders.domain.Order;
import me.jincrates.ecommerce.orders.domain.OrderStatus;

@Getter
public class OrderServiceResponse {

    private UUID id;  // 주문 ID
    private LocalDateTime orderedAt;  // 주문일자
    private OrderStatus orderStatus; // 주문 상태
    private List<OrderProductServiceResponse> orderProducts = new ArrayList<>();  // 주문 상품 list

    @Builder(access = AccessLevel.PRIVATE)
    private OrderServiceResponse(UUID id, LocalDateTime orderedAt, OrderStatus orderStatus,
        List<OrderProductServiceResponse> orderProducts) {
        this.id = id;
        this.orderedAt = orderedAt;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }

    public static OrderServiceResponse of(Order order) {
        return OrderServiceResponse.builder()
            .id(order.getId())
            .orderedAt(order.getCreatedAt())
            .orderStatus(order.getOrderStatus())
            .orderProducts(order.getOrderProducts().stream()
                .map(OrderProductServiceResponse::of)
                .collect(Collectors.toList()))
            .build();
    }
}
