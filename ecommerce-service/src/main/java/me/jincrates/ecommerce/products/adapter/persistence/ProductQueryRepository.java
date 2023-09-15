package me.jincrates.ecommerce.products.adapter.persistence;

import java.util.List;
import me.jincrates.ecommerce.products.application.service.request.ProductSearchServiceRequest;
import me.jincrates.ecommerce.products.domain.Product;
import org.springframework.data.domain.Pageable;

interface ProductQueryRepository {

    List<Product> findAllProduct(ProductSearchServiceRequest request, Pageable pageable);
}
