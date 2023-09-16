package me.jincrates.ecommerce.cart.adapter.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.application.service.response.CartServiceResponse;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "장바구니 response")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartResponse {

    @Schema(description = "장바구니 ID", example = "1")
    private Long id;

    @Schema(description = "회원 ID", example = "1")
    private Long memberId;

    @Schema(description = "장바구니 상품 목록")
    private List<CartProductResponse> cartProducts = new ArrayList<>();

    public CartResponse(Long id, Long memberId, List<CartProductResponse> cartProducts) {
        this.id = id;
        this.memberId = memberId;
        this.cartProducts = cartProducts;
    }

    public CartResponse(CartServiceResponse response) {
        this.id = response.getId();
        this.memberId = response.getId();
        this.cartProducts = response.getCartProducts().stream()
                .map(CartProductResponse::new)
                .toList();
    }
}
