package me.jincrates.api.ecommerce.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    CartProduct findByCartIdAndProductId(Long cartId, Long productId);
}
