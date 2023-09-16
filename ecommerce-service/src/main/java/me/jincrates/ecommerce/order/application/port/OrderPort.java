package me.jincrates.ecommerce.order.application.port;

import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderPort {

    Order saveOrder(Order order);

    Order findOrderById(UUID orderId);

    List<Order> findOrders(String email, Pageable pageable);

    Order findOrderByIdAndMember(UUID orderId, Member member);
}
