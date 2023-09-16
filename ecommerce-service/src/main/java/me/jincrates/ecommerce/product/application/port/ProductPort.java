package me.jincrates.ecommerce.product.application.port;

import me.jincrates.ecommerce.product.application.service.request.ProductSearchRequest;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductImage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductPort {

    Product saveProduct(Product product);

    Product findProductById(Long productId);

    List<Product> findAllProduct(ProductSearchRequest request, Pageable pageable);

    List<ProductImage> findProductImageByProductIdOrderByIdAsc(Long productId);
}
