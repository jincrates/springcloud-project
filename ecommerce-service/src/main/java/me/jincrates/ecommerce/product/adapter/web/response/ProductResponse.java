package me.jincrates.ecommerce.product.adapter.web.response;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.ecommerce.product.application.service.response.ProductImageServiceResponse;
import me.jincrates.ecommerce.product.application.service.response.ProductServiceResponse;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductImage;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

@Getter
public class ProductResponse {

    private Long id;  // 상품 ID
    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private ProductSellingStatus status;  // 상품 판매상태
    private List<ProductImageServiceResponse> productImages = new ArrayList<>();
    private List<Long> productImagesIds = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private ProductResponse(Long id, String productName, int price, String productDetail,
        ProductSellingStatus status, List<ProductImageServiceResponse> productImages,
        List<Long> productImagesIds) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.status = status;
        this.productImages = productImages;
        this.productImagesIds = productImagesIds;
    }

    public static ProductResponse of(Product product, List<ProductImage> productImages) {
        return ProductResponse.builder()
            .id(product.getId())
            .productName(product.getProductName())
            .price(product.getPrice())
            .productDetail(product.getProductDetail())
            .status(product.getStatus())
            .productImages(productImages.stream().map(ProductImageServiceResponse::of).toList())
            .productImagesIds(productImages.stream().map(ProductImage::getId).toList())
            .build();
    }

    public static ProductResponse of(ProductServiceResponse response) {
        return ProductResponse.builder()
            .id(response.getId())
            .productName(response.getProductName())
            .price(response.getPrice())
            .productDetail(response.getProductDetail())
            .status(response.getStatus())
            .build();
    }
}
