package me.jincrates.api.ecommerce.products.api.service.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.api.ecommerce.products.domain.product.ProductSellingStatus;

@Getter
public class ProductUpdateServiceRequest {
    private Long productId;
    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private ProductSellingStatus status;  // 판매상태

    @Builder
    private ProductUpdateServiceRequest(Long productId, String productName, int price, String productDetail, ProductSellingStatus status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.status = status;
    }
}
