package me.jincrates.ecommerce.cart.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.cart.domain.Cart;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "장바구니 response")
public record CartResponse(
        @Schema(description = "장바구니 ID", example = "1")
        Long id,
        @Schema(description = "회원 ID", example = "1")
        Long memberId,
        @Schema(description = "장바구니 상품 목록")
        List<CartProductResponse> cartProducts
) {

    public CartResponse {
        cartProducts = cartProducts == null ? new ArrayList<>() : cartProducts;
    }

    public static CartResponse of(Cart entity) {
        return new CartResponse(
                entity.getId(),
                entity.getMember().getId(),
                entity.getCartProducts().stream()
                        .map(CartProductResponse::of)
                        .toList()
        );
    }
}
