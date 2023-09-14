package me.jincrates.api.ecommerce.products.application.service.request;

import lombok.Getter;

@Getter
public class ProductCreateServiceRequest {

    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private int quantity; // 재고 수량

    public ProductCreateServiceRequest(String productName, int price, String productDetail,
                                       int quantity) {
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.quantity = quantity;
    }
}
