package me.jincrates.ecommerce.cart.adapter.web.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.application.service.response.CartProductServiceResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductResponse {
    private Long id;  // 장바구니 상품 ID
    private Long cartId;
    private Long productId;
    private int quantity;  // 담은 수량

    public CartProductResponse(Long id, Long cartId, Long productId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartProductResponse(CartProductServiceResponse response) {
    }
}
