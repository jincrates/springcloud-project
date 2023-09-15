package me.jincrates.ecommerce.order.application.port;

import java.util.List;
import java.util.UUID;
import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.domain.Pageable;

public interface OrderPort {

    Order saveOrder(Order order);

    Order findOrderById(UUID orderId);

    List<Order> findOrders(String email, Pageable pageable);
}
