package me.jincrates.ecommerce.product.adapter.persistence;

import me.jincrates.ecommerce.product.application.service.request.ProductSearchRequest;
import me.jincrates.ecommerce.product.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface ProductQueryRepository {

    List<Product> findAllProduct(ProductSearchRequest request, Pageable pageable);
}
