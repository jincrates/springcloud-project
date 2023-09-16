package me.jincrates.ecommerce.cart.adapter.web.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.application.service.request.CartCreateServiceRequest;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartCreateRequest {

    private List<CartProductRequest> cartProducts = new ArrayList<>();

    private Long memberId;

    @Builder(access = AccessLevel.PRIVATE)
    private CartCreateRequest(List<CartProductRequest> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public void assignMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public CartCreateServiceRequest toServiceRequest() {
        return null;
    }
}
