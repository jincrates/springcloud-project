package me.jincrates.ecommerce.cart.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "장바구니 상품 삭제 request")
public record CartProductDeleteRequest(
    @Schema(description = "장바구니 상품 ID", example = "1")
    @NotNull(message = "장바구니 상품는 필수입니다.")
    Long cartProductId
) {

}
