package me.jincrates.ecommerce.products.adapter.persistence;

import java.util.List;
import me.jincrates.ecommerce.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {

    List<Product> findAllByProductName(String productName);
}
