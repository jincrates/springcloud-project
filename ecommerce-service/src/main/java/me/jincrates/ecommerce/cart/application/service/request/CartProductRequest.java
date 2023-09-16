package me.jincrates.ecommerce.cart.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "장바구니 상품 request")
public record CartProductRequest(
        @Schema(description = "상품 ID", example = "1")
        @NotNull(message = "장바구니에 담을 상품의 ID가 없습니다.")
        Long productId,

        @Schema(description = "장바구니 수량", example = "1")
        @NotNull(message = "장바구니에 담을 상품의 개수를 입력해주세요.")
        @Min(value = 1, message = "장바구니에 담을 상품의 개수를 1개 이상으로 입력해주세요.")
        Integer quantity
) {

}
