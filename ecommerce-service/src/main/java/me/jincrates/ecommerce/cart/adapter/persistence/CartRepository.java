package me.jincrates.ecommerce.cart.adapter.persistence;

import me.jincrates.ecommerce.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByMemberId(Long memberId);
}
