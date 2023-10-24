package me.jincrates.ecommerce.order.application.port;

import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderPort {

    Order saveOrder(Order order);

    Order findOrderById(Long orderId);

    List<Order> findAllOrderByMemberId(Long memberId, Pageable pageable);

    Order findOrderByIdAndMemberId(Long orderId, Long memberId);
}
