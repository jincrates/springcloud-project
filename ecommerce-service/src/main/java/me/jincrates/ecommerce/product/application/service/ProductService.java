package me.jincrates.ecommerce.product.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
import me.jincrates.ecommerce.product.application.service.request.ProductCreateRequest;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.Stock;
import me.jincrates.ecommerce.store.application.port.StorePort;
import me.jincrates.ecommerce.store.domain.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements ProductUseCase {

    private final StorePort storePort;
    private final ProductPort productPort;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        Store store = storePort.findStoreById(request.storeId());
        Product product = Product.create(store, request.productName(), request.productDescription(), request.price(), request.sellingStatus());
        Stock stock = Stock.create(product, request.quantity());
        product.setStock(stock);
        Product savedProduct = productPort.saveProduct(product);

        return ProductResponse.of(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProduct(ProductSearchRequest request, Pageable pageable) {
        return productPort.findAllProduct(request, pageable).stream()
                .map(ProductResponse::of)
                .toList();
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        Product product = productPort.findProductById(productId);
        return ProductResponse.of(product);
    }
}
