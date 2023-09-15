package me.jincrates.ecommerce.product.adapter.persistence;

import java.util.Optional;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProduct(Product product);
}
