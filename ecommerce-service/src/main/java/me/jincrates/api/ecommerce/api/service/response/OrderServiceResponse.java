package me.jincrates.api.ecommerce.api.service.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import me.jincrates.api.ecommerce.domain.order.OrderStatus;

@Getter
public class OrderServiceResponse {

    private UUID id;  // 주문 ID
    private LocalDateTime orderedAt;  // 주문일자
    private OrderStatus orderStatus; // 주문 상태
    private List<OrderProductServiceResponse> orderProducts = new ArrayList<>();  // 주문 상품 list
}
