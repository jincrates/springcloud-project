package me.jincrates.ecommerce.product.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.adapter.web.response.ProductResponse;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductImage;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductServiceResponse {

    private Long id;  // 상품 ID
    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private ProductSellingStatus status;  // 상품 판매상태
    private List<ProductImageServiceResponse> productImages = new ArrayList<>();
    private List<Long> productImagesIds = new ArrayList<>();

    public ProductServiceResponse(Long id, String productName, int price, String productDetail,
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

    public static ProductServiceResponse of(Product product) {
        return new ProductServiceResponse(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getProductDetail(),
                product.getStatus(),
                null,
                null
        );
    }

    public static ProductServiceResponse of(Product product, List<ProductImage> productImages) {
        return new ProductServiceResponse(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getProductDetail(),
                product.getStatus(),
                productImages.stream().map(ProductImageServiceResponse::of).toList(),
                productImages.stream().map(ProductImage::getId).toList()
        );
    }

    public ProductResponse toResponse() {
        return ProductResponse.of(this);
    }
}
