package me.jincrates.ecommerce.product.application.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUpdateServiceRequest {

    private Long productId;
    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private ProductSellingStatus status;  // 판매상태

    public ProductUpdateServiceRequest(Long productId, String productName, int price,
                                       String productDetail, ProductSellingStatus status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.status = status;
    }
}
