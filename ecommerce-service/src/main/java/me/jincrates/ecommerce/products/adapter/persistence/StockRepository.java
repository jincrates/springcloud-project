package me.jincrates.ecommerce.products.adapter.persistence;

import java.util.Optional;
import me.jincrates.ecommerce.products.domain.Product;
import me.jincrates.ecommerce.products.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProduct(Product product);
}
