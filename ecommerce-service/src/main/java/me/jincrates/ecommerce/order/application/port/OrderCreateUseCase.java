package me.jincrates.ecommerce.order.application.port;

import me.jincrates.ecommerce.order.application.service.request.OrderCreateRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderResponse;

public interface OrderCreateUseCase {

    OrderResponse createOrder(OrderCreateRequest request, Long memberId);
}
