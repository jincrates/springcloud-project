package me.jincrates.api.ecommerce.products.adapter.database;

import me.jincrates.api.ecommerce.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {

    List<Product> findAllByProductName(String productName);
}
