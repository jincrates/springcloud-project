package me.jincrates.api.ecommerce.products.application.port;

import me.jincrates.api.ecommerce.products.application.service.request.ProductSearchServiceRequest;
import me.jincrates.api.ecommerce.products.domain.Product;
import me.jincrates.api.ecommerce.products.domain.ProductImage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductPort {

    Product saveProduct(Product product);

    Product findProductById(Long productId);

    List<Product> findAllProduct(ProductSearchServiceRequest request, Pageable pageable);

    List<ProductImage> findProductImageByProductIdOrderByIdAsc(Long productId);
}
