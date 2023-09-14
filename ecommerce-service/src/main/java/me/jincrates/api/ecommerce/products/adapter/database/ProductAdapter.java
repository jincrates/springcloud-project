package me.jincrates.api.ecommerce.products.adapter.database;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.products.application.port.ProductPort;
import me.jincrates.api.ecommerce.products.application.port.StockPort;
import me.jincrates.api.ecommerce.products.application.service.request.ProductSearchServiceRequest;
import me.jincrates.api.ecommerce.products.domain.Product;
import me.jincrates.api.ecommerce.products.domain.ProductImage;
import me.jincrates.api.ecommerce.products.domain.Stock;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class ProductAdapter implements ProductPort, StockPort {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final StockRepository stockRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다. productId=" + productId));
    }

    @Override
    public List<Product> findAllProduct(ProductSearchServiceRequest request, Pageable pageable) {
        return productRepository.findAllProduct(request, pageable);
    }

    @Override
    public List<ProductImage> findProductImageByProductIdOrderByIdAsc(Long productId) {
        return productImageRepository.findByProductIdOrderByIdAsc(productId);
    }

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }
}
