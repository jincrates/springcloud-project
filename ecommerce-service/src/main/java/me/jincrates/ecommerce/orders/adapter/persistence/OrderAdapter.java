package me.jincrates.ecommerce.orders.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.orders.application.port.OrderPort;
import me.jincrates.ecommerce.orders.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderAdapter implements OrderPort {

    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("주문을 찾을 수 없습니다. orderId=" + orderId));
    }

    @Override
    public List<Order> findOrders(String email, Pageable pageable) {
        return orderRepository.findOrders(email, pageable);
    }
}
