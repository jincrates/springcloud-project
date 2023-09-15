package me.jincrates.ecommerce.orders.adapter.persistence;

import java.util.UUID;
import me.jincrates.ecommerce.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>, OrderQueryRepository {

}
