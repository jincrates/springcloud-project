package me.jincrates.ecommerce.product.application.port;

import me.jincrates.ecommerce.product.application.service.request.ProductCreateServiceRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductServiceResponse;

public interface ProductUseCase {

    ProductServiceResponse createProduct(ProductCreateServiceRequest request);
}
