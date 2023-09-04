package me.jincrates.api.ecommerce.domain.product;

import me.jincrates.api.ecommerce.api.service.request.ProductSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {
    Page<Product> getAdminProductPage(ProductSearchRequest request, Pageable pageable);
}
