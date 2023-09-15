package me.jincrates.api.ecommerce.carts.adapter.persistence;

import me.jincrates.api.ecommerce.carts.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByMemberId(Long memberId);
}
