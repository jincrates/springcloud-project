package me.jincrates.api.ecommerce.domain.order;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findOrders(String email, Pageable pageable);

    Long countOrder(String email);
}
