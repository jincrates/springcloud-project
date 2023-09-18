package me.jincrates.ecommerce.product.application.port;

import java.util.List;
import me.jincrates.ecommerce.product.application.service.request.ProductCreateRequest;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;
import org.springframework.data.domain.Pageable;

public interface ProductUseCase {

    ProductResponse createProduct(ProductCreateRequest request);

    List<ProductResponse> getAllProduct(ProductSearchRequest request, Pageable pageable);

    ProductResponse getProduct(Long productId);
}
