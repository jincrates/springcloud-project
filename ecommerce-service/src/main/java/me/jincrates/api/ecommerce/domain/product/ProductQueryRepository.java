package me.jincrates.api.ecommerce.domain.product;

import me.jincrates.api.ecommerce.api.service.request.ProductSearchServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {

    Page<Product> getAdminProductPage(ProductSearchServiceRequest request, Pageable pageable);
}
