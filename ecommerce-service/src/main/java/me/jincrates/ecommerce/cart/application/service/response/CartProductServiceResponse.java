package me.jincrates.ecommerce.cart.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.domain.CartProduct;
import me.jincrates.ecommerce.product.application.service.response.ProductServiceResponse;
import me.jincrates.ecommerce.product.domain.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductServiceResponse {
    private Long id;  // 장바구니 상품 ID
    private Long cartId;
    private ProductServiceResponse product;
    private int quantity;  // 담은 수량

    public CartProductServiceResponse(Long id, Long cartId, Product product, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.product = ProductServiceResponse.of(product);
        this.quantity = quantity;
    }

    public CartProductServiceResponse(CartProduct entity) {
        this.id = entity.getId();
        this.cartId = entity.getCart().getId();
        this.product = ProductServiceResponse.of(entity.getProduct());
        this.quantity = entity.getQuantity();
    }
}
