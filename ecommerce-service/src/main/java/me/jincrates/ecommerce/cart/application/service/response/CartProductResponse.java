package me.jincrates.ecommerce.cart.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.cart.domain.CartProduct;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;

@Schema(description = "장바구니 상품 response")
public record CartProductResponse(
        @Schema(description = "장바구니 상품 ID", example = "1")
        Long id,
        @Schema(description = "장바구니 ID", example = "1")
        Long cartId,
        @Schema(description = "상품")
        ProductResponse product,
        @Schema(description = "장바구니에 담은 상품 수량", example = "1")
        Integer quantity
) {
    public static CartProductResponse of(CartProduct entity) {
        return new CartProductResponse(
                entity.getId(),
                entity.getCart().getId(),
                ProductResponse.of(entity.getProduct()),
                entity.getQuantity()
        );
    }
}
