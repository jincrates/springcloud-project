package me.jincrates.api.ecommerce.api.service.request;

import lombok.Getter;

@Getter
public class CartProductServiceRequest {

    private Long productId;  // 상품 ID
    private int quantity;  // 수량

    public CartProductServiceRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
