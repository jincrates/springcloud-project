package me.jincrates.ecommerce.order.adapter.persistence;

import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface OrderQueryRepository {

    List<Order> findOrders(String email, Pageable pageable);

    Long countOrder(String email);
}
