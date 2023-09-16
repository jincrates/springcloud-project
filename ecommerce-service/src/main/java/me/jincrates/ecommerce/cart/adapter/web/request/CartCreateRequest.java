package me.jincrates.ecommerce.cart.adapter.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.cart.application.service.request.CartCreateServiceRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductServiceRequest;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "장바구니 등록 request")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartCreateRequest {

    @Schema(description = "장바구니 상품 리스트")
    @NotEmpty(message = "장바구니에 담을 추가해 주세요.")
    @Size(min = 1, message = "장바구니에 담을 상품을 추가해 주세요.")
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
        return new CartCreateServiceRequest(
                this.cartProducts.stream().map(CartProductServiceRequest::new).toList(),
                this.memberId
        );
    }
}
