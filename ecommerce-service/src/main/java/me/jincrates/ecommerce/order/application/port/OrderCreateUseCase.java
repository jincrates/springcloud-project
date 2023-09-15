package me.jincrates.ecommerce.order.application.port;

import me.jincrates.ecommerce.order.application.service.request.OrderCreateServiceRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderServiceResponse;

public interface OrderCreateUseCase {

    OrderServiceResponse createOrder(OrderCreateServiceRequest request);
}
