package me.jincrates.ecommerce.cart.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.domain.Cart;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartServiceResponse {
    private Long id;
    private Long memberId;
    private List<CartProductServiceResponse> cartProducts = new ArrayList<>();

    public CartServiceResponse(Long id, Long memberId, List<CartProductServiceResponse> cartProducts) {
        this.id = id;
        this.memberId = memberId;
        this.cartProducts = cartProducts;
    }

    public CartServiceResponse(Cart entity) {
        this.id = entity.getId();
        this.memberId = entity.getMember().getId();
        this.cartProducts = entity.getCartProducts().stream()
                .map(CartProductServiceResponse::new)
                .toList();
    }
}
