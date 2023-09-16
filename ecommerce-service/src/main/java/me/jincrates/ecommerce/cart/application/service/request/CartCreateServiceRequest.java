package me.jincrates.ecommerce.cart.application.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartCreateServiceRequest {

    private List<CartProductServiceRequest> cartProducts = new ArrayList<>();

    private Long memberId;

    public CartCreateServiceRequest(List<CartProductServiceRequest> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public void assignMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public CartCreateServiceRequest(List<CartProductServiceRequest> cartProducts, Long memberId) {
        this.cartProducts = cartProducts;
        this.memberId = memberId;
    }
}
