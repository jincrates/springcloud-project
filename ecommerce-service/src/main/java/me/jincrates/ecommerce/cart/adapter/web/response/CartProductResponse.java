package me.jincrates.ecommerce.cart.adapter.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.application.service.response.CartProductServiceResponse;
import me.jincrates.ecommerce.product.adapter.web.response.ProductResponse;
import me.jincrates.ecommerce.product.application.service.response.ProductServiceResponse;

@Schema(description = "장바구니 상품 response")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductResponse {

    @Schema(description = "장바구니 상품 ID", example = "1")
    private Long id;  // 장바구니 상품 ID

    @Schema(description = "장바구니 ID", example = "1")
    private Long cartId;

    @Schema(description = "상품")
    private ProductResponse product;

    @Schema(description = "장바구니에 담은 상품 수량", example = "1")
    private int quantity;  // 담은 수량

    public CartProductResponse(Long id, Long cartId, ProductServiceResponse product, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.product = ProductResponse.of(product);
        this.quantity = quantity;
    }

    public CartProductResponse(CartProductServiceResponse response) {
        this.id = response.getId();
        this.cartId = response.getCartId();
        this.product = ProductResponse.of(response.getProduct());
        this.quantity = response.getQuantity();
    }
}
