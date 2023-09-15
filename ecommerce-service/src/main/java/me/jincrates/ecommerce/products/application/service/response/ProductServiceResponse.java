package me.jincrates.ecommerce.products.application.service.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.ecommerce.products.domain.Product;
import me.jincrates.ecommerce.products.domain.ProductImage;
import me.jincrates.ecommerce.products.domain.ProductSellingStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductServiceResponse {
    private Long id;  // 상품 ID
    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private ProductSellingStatus status;  // 상품 판매상태
    private List<ProductImageServiceResponse> productImages = new ArrayList<>();
    private List<Long> productImagesIds = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private ProductServiceResponse(Long id, String productName, int price, String productDetail, ProductSellingStatus status, List<ProductImageServiceResponse> productImages, List<Long> productImagesIds) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.status = status;
        this.productImages = productImages;
        this.productImagesIds = productImagesIds;
    }

    public static ProductServiceResponse of(Product product, List<ProductImage> productImages) {
        return ProductServiceResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .productDetail(product.getProductDetail())
                .status(product.getStatus())
                .productImages(productImages.stream().map(ProductImageServiceResponse::of).toList())
                .productImagesIds(productImages.stream().map(ProductImage::getId).toList())
                .build();
    }
}
