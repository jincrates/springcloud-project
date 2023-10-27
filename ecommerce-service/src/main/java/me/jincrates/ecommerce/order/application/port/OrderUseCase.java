package me.jincrates.ecommerce.order.application.port;

import me.jincrates.ecommerce.order.application.service.request.OrderCreateRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderUseCase {
    OrderResponse createOrder(OrderCreateRequest request, Long memberId);

    OrderResponse cancelOrder(Long orderId, Long memberId);

    List<OrderResponse> getOrders(Long memberId, Pageable pageable);

    OrderResponse getOrder(Long orderId, Long memberId);
}
