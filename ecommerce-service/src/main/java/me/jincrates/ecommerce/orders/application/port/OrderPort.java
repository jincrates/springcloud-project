package me.jincrates.ecommerce.orders.application.port;

import java.util.List;
import java.util.UUID;
import me.jincrates.ecommerce.orders.domain.Order;
import org.springframework.data.domain.Pageable;

public interface OrderPort {

    Order saveOrder(Order order);

    Order findOrderById(UUID orderId);

    List<Order> findOrders(String email, Pageable pageable);
}
