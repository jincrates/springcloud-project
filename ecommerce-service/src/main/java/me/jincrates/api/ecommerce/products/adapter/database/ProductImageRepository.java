package me.jincrates.api.ecommerce.products.adapter.database;

import me.jincrates.api.ecommerce.products.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductIdOrderByIdAsc(Long productId);
}
