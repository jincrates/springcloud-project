package me.jincrates.ecommerce.order.application.port;

import me.jincrates.ecommerce.order.application.service.request.OrderCancelServiceRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderServiceResponse;

public interface OrderCancelUseCase {

    OrderServiceResponse cancelOrder(OrderCancelServiceRequest request);
}
