package me.jincrates.ecommerce.order.adapter.persistence;

import java.util.List;
import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.domain.Pageable;

public interface OrderQueryRepository {

    List<Order> findOrders(String email, Pageable pageable);

    Long countOrder(String email);
}
