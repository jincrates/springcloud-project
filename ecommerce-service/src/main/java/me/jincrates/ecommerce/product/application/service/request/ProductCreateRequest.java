package me.jincrates.ecommerce.product.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "상품 등록 request")
public record ProductCreateRequest(
        @Schema(description = "상품명", example = "소크라테스의 변명")
        @NotBlank(message = "상품명은 필수입니다.")
        String productName,
        @Schema(description = "상품 가격", example = "10000")
        @Min(value = 0, message = "상품 가격은 0원 이상이어야 합니다.")
        @NotNull(message = "상품 가격은 필수입니다.")
        Integer price,
        @Schema(description = "상품 상세 설명", example = "《소크라테스의 변명》은 인류 역사상 가장 위대한 철학자인 소크라테스의 영혼의 책이다.")
        @NotBlank(message = "상품 상세 설명은 필수입니다.")
        String productDetail,
        @Schema(description = "재고 수량", example = "100")
        @Min(value = 0, message = "재고 수량 0개 이상이어야 합니다.")
        @NotNull(message = "재고 수량은 필수입니다.")
        Integer quantity
) {

}
