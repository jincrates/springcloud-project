package me.jincrates.ecommerce.product.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.StockPort;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchServiceRequest;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductImage;
import me.jincrates.ecommerce.product.domain.Stock;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

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
            .orElseThrow(
                () -> new EntityNotFoundException("상품을 찾을 수 없습니다. productId=" + productId));
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

    @Override
    public Stock findStockByProduct(Product product) {
        return stockRepository.findByProduct(product)
            .orElseThrow(() -> new EntityNotFoundException(
                "상품 재고를 찾을 수 없습니다. productId=" + product.getId()));
    }
}
