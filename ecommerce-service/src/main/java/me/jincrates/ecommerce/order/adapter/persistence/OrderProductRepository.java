package me.jincrates.ecommerce.order.adapter.persistence;

import me.jincrates.ecommerce.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderProductRepository extends JpaRepository<OrderItem, Long> {

}
