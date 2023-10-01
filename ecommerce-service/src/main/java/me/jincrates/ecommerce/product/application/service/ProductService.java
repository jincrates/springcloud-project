package me.jincrates.ecommerce.product.application.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
import me.jincrates.ecommerce.product.application.port.StockPort;
import me.jincrates.ecommerce.product.application.service.request.ProductCreateRequest;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;
import me.jincrates.ecommerce.product.domain.Product;
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

    private final ProductPort productPort;
    private final StockPort stockPort;
    private final ProductImageService productImageService;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
//        // 상품 등록
//        Product product = Product.create(request.productName(), request.price(),
//            request.productDetail());
//        productPort.saveProduct(product);
//
//        // 재고 등록
//        stockPort.saveStock(Stock.create(product, request.quantity()));
//
//        // TODO: 판매 상태를 전달받아서 처리하도록 수정 필요
//        product.selling();
//
//        return ProductResponse.of(product);
        return null;
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

//    @Transactional
//    public Long createProduct(ProductCreateRequest request, List<MultipartFile> images) {
//        // 상품 등록
//        Product product = Product.create(request.productName(), request.price(),
//                request.productDetail());
//        productPort.saveProduct(product);
//
//        // 재고 등록
//        stockPort.saveStock(Stock.create(product, request.quantity()));
//
//        // 이미지 등록
//        for (int i = 0; i < images.size(); i++) {
//            ProductImage productImage = new ProductImage(product);
//
//            if (i == 0) {
//                productImage.represented();
//            }
//            productImageService.saveProductImage(productImage, images.get(i));
//        }
//
//        return product.getId();
//    }
//
//    public ProductResponse getProductDetail(Long productId) {
//        List<ProductImage> productImages = productPort.findProductImageByProductIdOrderByIdAsc(productId);
//
//        Product product = productPort.findProductById(productId);
//
//        return ProductResponse.of(product);
//    }
//
//    @Transactional
//    public Long updateProduct(ProductUpdateRequest request, List<MultipartFile> images) {
//        // 상품 조회
//        Product product = productPort.findProductById(request.productId());
//        product.update(request);
//
//        ProductResponse productDetail = getProductDetail(request.productId());
//        List<Long> productImagesIds = productDetail.productImagesIds();
//
//        // 이미지 수정
//        for (int i = 0; i < images.size(); i++) {
//            productImageService.updateProductImage(productImagesIds.get(i), images.get(i));
//        }
//
//        return product.getId();
//    }
//
//    public PageResponse<?> getProducts(ProductSearchRequest request, Pageable pageable) {
//        List<Product> products = productPort.findAllProduct(request, pageable);
//
//        return PageResponse.builder()
//                .pageNo(pageable.getPageNumber())
//                .hasNext(products.size() > pageable.getPageSize())
//                .contents(Collections.singletonList(products.subList(0, pageable.getPageSize())))
//                .build();
//    }
}
