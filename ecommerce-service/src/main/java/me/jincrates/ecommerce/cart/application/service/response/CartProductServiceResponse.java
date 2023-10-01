package me.jincrates.ecommerce.cart.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.domain.CartItem;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;
import me.jincrates.ecommerce.product.domain.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductServiceResponse {
    private Long id;  // 장바구니 상품 ID
    private Long cartId;
    private ProductResponse product;
    private Integer quantity;  // 담은 수량

    public CartProductServiceResponse(Long id, Long cartId, Product product, Integer quantity) {
        this.id = id;
        this.cartId = cartId;
        this.product = ProductResponse.of(product);
        this.quantity = quantity;
    }

    public CartProductServiceResponse(CartItem entity) {
        this.id = entity.getId();
        this.cartId = entity.getCart().getId();
        this.product = ProductResponse.of(entity.getProduct());
        this.quantity = entity.getQuantity();
    }
}
