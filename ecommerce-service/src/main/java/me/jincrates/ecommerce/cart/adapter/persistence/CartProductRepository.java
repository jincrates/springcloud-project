package me.jincrates.ecommerce.cart.adapter.persistence;

import me.jincrates.ecommerce.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CartProductRepository extends JpaRepository<CartItem, Long>,
        CartProductQueryRepository {

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    Optional<CartItem> findByIdAndCartId(Long cartProductId, Long cartId);
}
