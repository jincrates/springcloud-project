package me.jincrates.ecommerce.order.application.port;

import me.jincrates.ecommerce.order.application.service.request.OrderCancelRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderResponse;

public interface OrderCancelUseCase {

    OrderResponse cancelOrder(OrderCancelRequest request, Long memberId);
}
