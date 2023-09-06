package me.jincrates.api.ecommerce.domain.stock;

import me.jincrates.api.ecommerce.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByProduct(Product product);
}
