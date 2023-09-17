package me.jincrates.ecommerce.product.adapter.persistence;

import me.jincrates.ecommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {

    List<Product> findAllByName(String productName);
}
