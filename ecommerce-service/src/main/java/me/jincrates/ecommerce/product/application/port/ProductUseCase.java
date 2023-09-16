package me.jincrates.ecommerce.product.application.port;

import me.jincrates.ecommerce.product.application.service.request.ProductCreateRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;

public interface ProductUseCase {

    ProductResponse createProduct(ProductCreateRequest request);
}
