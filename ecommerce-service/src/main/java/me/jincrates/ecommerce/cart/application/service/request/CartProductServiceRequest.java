package me.jincrates.ecommerce.cart.application.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.adapter.web.request.CartProductRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductServiceRequest {

    private Long productId;  // 상품 ID
    private int quantity;  // 수량

    public CartProductServiceRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartProductServiceRequest(CartProductRequest request) {
        this.productId = request.getProductId();
        this.quantity = request.getQuantity();
    }
}
