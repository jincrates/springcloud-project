package me.jincrates.api.ecommerce.domain.product;

import me.jincrates.api.ecommerce.api.service.request.ProductSearchServiceRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductQueryRepository {

    List<Product> getAdminProducts(ProductSearchServiceRequest request, Pageable pageable);
}
