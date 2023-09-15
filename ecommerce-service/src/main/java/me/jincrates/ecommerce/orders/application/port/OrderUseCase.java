package me.jincrates.ecommerce.orders.application.port;

import java.util.List;
import java.util.UUID;
import me.jincrates.ecommerce.orders.application.service.request.OrderCreateServiceRequest;
import me.jincrates.ecommerce.orders.application.service.response.OrderServiceResponse;
import me.jincrates.global.common.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface OrderUseCase {

    OrderServiceResponse order(OrderCreateServiceRequest request, String email);

    OrderServiceResponse orders(List<OrderCreateServiceRequest> requests, String email);

    OrderServiceResponse orderFromCart(String email);

    PageResponse<?> getOrders(String email, Pageable pageable);

    boolean validateOrder(UUID orderId, String email);

    void cancel(UUID orderId);
}
