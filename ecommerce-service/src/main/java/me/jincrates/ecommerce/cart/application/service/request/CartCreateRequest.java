package me.jincrates.ecommerce.cart.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "장바구니 담기 request")
public record CartCreateRequest(
        @Schema(description = "장바구니 상품 목록")
        @Valid
        @NotEmpty(message = "장바구니에 담을 상품을 추가해 주세요.")
        @Size(min = 1, message = "장바구니에 담을 상품을 추가해 주세요.")
        List<CartProductRequest> cartProducts
) {

    public CartCreateRequest {
        cartProducts = cartProducts == null ? new ArrayList<>() : cartProducts;
    }
}