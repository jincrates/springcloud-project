package me.jincrates.ecommerce.order.adapter.persistence;

import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryRepository {

    Optional<Order> findByIdAndMemberId(Long orderId, Long memberId);

    Page<Order> findAllByMemberId(Long memberId, Pageable pageable);
}
