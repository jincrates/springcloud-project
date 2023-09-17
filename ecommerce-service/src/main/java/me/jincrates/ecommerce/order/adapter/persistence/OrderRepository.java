package me.jincrates.ecommerce.order.adapter.persistence;

import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface OrderRepository extends JpaRepository<Order, UUID>, OrderQueryRepository {

    Optional<Order> findByIdAndMember(UUID orderId, Member member);
}
