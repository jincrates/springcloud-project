package me.jincrates.api.ecommerce.products.adapter.database;

import me.jincrates.api.ecommerce.products.application.service.request.ProductSearchServiceRequest;
import me.jincrates.api.ecommerce.products.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface ProductQueryRepository {

    List<Product> findAllProduct(ProductSearchServiceRequest request, Pageable pageable);
}
