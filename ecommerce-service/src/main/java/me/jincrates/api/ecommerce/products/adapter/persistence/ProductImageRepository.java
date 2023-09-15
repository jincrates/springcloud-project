package me.jincrates.api.ecommerce.products.adapter.persistence;

import java.util.List;
import me.jincrates.api.ecommerce.products.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductIdOrderByIdAsc(Long productId);
}
