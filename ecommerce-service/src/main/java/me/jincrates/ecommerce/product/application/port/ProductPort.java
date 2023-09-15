package me.jincrates.ecommerce.product.application.port;

import java.util.List;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchServiceRequest;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductImage;
import org.springframework.data.domain.Pageable;

public interface ProductPort {

    Product saveProduct(Product product);

    Product findProductById(Long productId);

    List<Product> findAllProduct(ProductSearchServiceRequest request, Pageable pageable);

    List<ProductImage> findProductImageByProductIdOrderByIdAsc(Long productId);
}
