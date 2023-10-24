package me.jincrates.ecommerce.order.application.port;

import me.jincrates.ecommerce.order.application.service.response.OrderResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderCancelUseCase {

    OrderResponse cancelOrder(Long orderId, Long memberId);

    List<OrderResponse> getOrders(Long memberId, Pageable pageable);
}
