package me.jincrates.ecommerce.cart.adapter.persistence;

import java.util.Optional;
import me.jincrates.ecommerce.cart.domain.Cart;
import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByMember(Member member);

    Optional<Cart> findByMemberId(Long memberId);
}
