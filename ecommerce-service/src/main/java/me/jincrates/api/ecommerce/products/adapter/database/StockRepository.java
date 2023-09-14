package me.jincrates.api.ecommerce.products.adapter.database;

import me.jincrates.api.ecommerce.products.domain.Product;
import me.jincrates.api.ecommerce.products.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProduct(Product product);
}
