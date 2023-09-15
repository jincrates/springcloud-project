package me.jincrates.ecommerce.product.adapter.persistence;

import java.util.List;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchServiceRequest;
import me.jincrates.ecommerce.product.domain.Product;
import org.springframework.data.domain.Pageable;

interface ProductQueryRepository {

    List<Product> findAllProduct(ProductSearchServiceRequest request, Pageable pageable);
}
