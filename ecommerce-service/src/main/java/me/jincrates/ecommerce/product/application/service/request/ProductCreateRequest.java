package me.jincrates.ecommerce.product.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "상품 등록 request")
public record ProductCreateRequest(
        @Schema(description = "상점 ID", example = "1")
        @NotNull(message = "상점 ID는 필수입니다.")
        Long storeId,
        @Schema(description = "상품명", example = "에스프레소")
        @NotBlank(message = "상품명은 필수입니다.")
        String productName,

        @Schema(description = "상품 상세 설명", example = "풍미가 있습니다.")
        @NotBlank(message = "상품 상세 설명은 필수입니다.")
        String productDescription,

        @Schema(description = "상품 가격", example = "3000")
        @Min(value = 0, message = "상품 가격은 0원 이상이어야 합니다.")
        @NotNull(message = "상품 가격은 필수입니다.")
        Integer price,

        @Schema(description = "상품 판매상태", example = "SELLING")
        @NotNull(message = "상품 판매상태는 필수입니다.")
        ProductSellingStatus sellingStatus,

        @Schema(description = "재고 수량", example = "100")
        @Min(value = 0, message = "재고 수량 0개 이상이어야 합니다.")
        @NotNull(message = "재고 수량은 필수입니다.")
        Integer quantity,

        @Schema(description = "업로드 이미지 url 목록")
        List<String> imageUrls
) {

    public ProductCreateRequest {
        imageUrls = imageUrls == null ? new ArrayList<>() : imageUrls;
    }
}
