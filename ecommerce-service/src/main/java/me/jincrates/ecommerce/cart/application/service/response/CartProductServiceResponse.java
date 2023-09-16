package me.jincrates.ecommerce.cart.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.domain.CartProduct;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductServiceResponse {
    private Long id;  // 장바구니 상품 ID
    private Long cartId;
    private Long productId;
    private int quantity;  // 담은 수량

    public CartProductServiceResponse(Long id, Long cartId, Long productId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartProductServiceResponse(CartProduct entity) {

    }
}
