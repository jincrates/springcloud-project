package me.jincrates.ecommerce.cart.adapter.persistence;

import me.jincrates.ecommerce.cart.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CartProductRepository extends JpaRepository<CartProduct, Long>,
    CartProductQueryRepository {

    CartProduct findByCartIdAndProductId(Long cartId, Long productId);
}
