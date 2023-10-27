package me.jincrates.ecommerce.product.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

@Schema(description = "상품 조회 request")
public record ProductSearchRequest(
        @Schema(description = "검색일자 타입(all, 1d, 1w, 1m, 6m)", example = "all")
        String searchDateType,
        @Schema(description = "상품 상태", example = "")
        ProductSellingStatus searchStatus,
        @Schema(description = "검색 조건(productName, createdBy)", example = "productName")
        String searchBy,
        @Schema(description = "검색 키워드", example = "에스프레소")
        String searchQuery
) {

}
