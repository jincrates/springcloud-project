package me.jincrates.api.ecommerce.products.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository{

    List<Product> findAllByProductName(String productName);
}
