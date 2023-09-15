package me.jincrates.ecommerce.product.application.service.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.ecommerce.product.adapter.web.request.ProductCreateRequest;

@Getter
public class ProductCreateServiceRequest {

    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private int quantity; // 재고 수량

    @Builder(access = AccessLevel.PRIVATE)
    private ProductCreateServiceRequest(String productName, int price, String productDetail,
        int quantity) {
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.quantity = quantity;
    }

    public static ProductCreateServiceRequest of(ProductCreateRequest request) {
        return ProductCreateServiceRequest.builder()
            .productName(request.getProductName())
            .price(request.getPrice())
            .productDetail(request.getProductDetail())
            .quantity(request.getQuantity())
            .build();
    }
}
