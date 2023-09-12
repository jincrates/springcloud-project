package me.jincrates.api.ecommerce.domain.order;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderQueryRepository {

    List<Order> findOrders(String email, Pageable pageable);

    Long countOrder(String email);
}
