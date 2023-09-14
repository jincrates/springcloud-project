package me.jincrates.api.ecommerce.carts.adapter.database;

import me.jincrates.api.ecommerce.carts.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long>,
    CartProductQueryRepository {

    CartProduct findByCartIdAndProductId(Long cartId, Long productId);
}
