package me.jincrates.ecommerce.cart.application.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.adapter.web.request.CartProductRequest;
import me.jincrates.ecommerce.order.application.service.request.OrderCreateServiceRequest;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartCreateServiceRequest {

    private List<CartProductRequest> cartProducts = new ArrayList<>();

    private Long memberId;

    public CartCreateServiceRequest(List<CartProductRequest> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public void assignMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return null;
    }
}
