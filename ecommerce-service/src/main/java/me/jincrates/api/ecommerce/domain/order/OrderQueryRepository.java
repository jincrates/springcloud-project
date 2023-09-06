package me.jincrates.api.ecommerce.domain.order;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderQueryRepository {

    List<Order> findOrders(String email, Pageable pageable);

    Long countOrder(String email);
}
