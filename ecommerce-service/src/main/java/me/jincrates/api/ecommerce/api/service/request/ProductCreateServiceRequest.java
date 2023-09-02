package me.jincrates.api.ecommerce.api.service.request;

import lombok.Getter;

@Getter
public class ProductCreateServiceRequest {
    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명

    public ProductCreateServiceRequest(String productName, int price, String productDetail) {
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
    }
}
