package me.jincrates.ecommerce.product.application.service;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
import me.jincrates.ecommerce.product.application.port.StockPort;
import me.jincrates.ecommerce.product.application.service.request.ProductCreateServiceRequest;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchServiceRequest;
import me.jincrates.ecommerce.product.application.service.request.ProductUpdateServiceRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductServiceResponse;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductImage;
import me.jincrates.ecommerce.product.domain.Stock;
import me.jincrates.global.common.response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Service
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements ProductUseCase {

    private final ProductPort productPort;
    private final StockPort stockPort;
    private final ProductImageService productImageService;

    @Override
    public ProductServiceResponse createProduct(ProductCreateServiceRequest request) {
        // 상품 등록
        Product product = Product.create(request.getProductName(), request.getPrice(),
            request.getProductDetail());
        productPort.saveProduct(product);

        // 재고 등록
        stockPort.saveStock(Stock.create(product, request.getQuantity()));

        return ProductServiceResponse.of(product);
    }

    @Transactional
    public Long createProduct(ProductCreateServiceRequest request, List<MultipartFile> images) {
        // 상품 등록
        Product product = Product.create(request.getProductName(), request.getPrice(),
            request.getProductDetail());
        productPort.saveProduct(product);

        // 재고 등록
        stockPort.saveStock(Stock.create(product, request.getQuantity()));

        // 이미지 등록
        for (int i = 0; i < images.size(); i++) {
            ProductImage productImage = new ProductImage(product);

            if (i == 0) {
                productImage.represented();
            }
            productImageService.saveProductImage(productImage, images.get(i));
        }

        return product.getId();
    }

    public ProductServiceResponse getProductDetail(Long productId) {
        List<ProductImage> productImages = productPort.findProductImageByProductIdOrderByIdAsc(
            productId);

        Product product = productPort.findProductById(productId);

        return ProductServiceResponse.of(product, productImages);
    }

    @Transactional
    public Long updateProduct(ProductUpdateServiceRequest request, List<MultipartFile> images) {
        // 상품 조회
        Product product = productPort.findProductById(request.getProductId());
        product.update(request);

        ProductServiceResponse productDetail = getProductDetail(request.getProductId());
        List<Long> productImagesIds = productDetail.getProductImagesIds();

        // 이미지 수정
        for (int i = 0; i < images.size(); i++) {
            productImageService.updateProductImage(productImagesIds.get(i), images.get(i));
        }

        return product.getId();
    }

    public PageResponse<?> getProducts(ProductSearchServiceRequest request, Pageable pageable) {
        List<Product> products = productPort.findAllProduct(request, pageable);

        return PageResponse.builder()
            .pageNo(pageable.getPageNumber())
            .hasNext(products.size() > pageable.getPageSize())
            .contents(Collections.singletonList(products.subList(0, pageable.getPageSize())))
            .build();
    }
}
