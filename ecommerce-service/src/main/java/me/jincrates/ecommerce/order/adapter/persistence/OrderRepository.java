package me.jincrates.ecommerce.order.adapter.persistence;

import java.util.UUID;
import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>, OrderQueryRepository {

}
