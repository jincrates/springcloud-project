package me.jincrates.ecommerce.product.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

@Schema(description = "상품 response")
public record ProductResponse(
        @Schema(description = "상점 ID")
        Long storeId,
        @Schema(description = "상품 ID")
        Long productId,
        @Schema(description = "상품명")
        String productName,
        @Schema(description = "상품 상세 설명")
        String productDescription,
        @Schema(description = "상품 가격")
        Integer price,
        @Schema(description = "상품 판매상태")
        ProductSellingStatus status,
        @Schema(description = "상품 재고")
        StockResponse stock
) {
    public static ProductResponse of(Product product) {
        return new ProductResponse(
                product.getStore().getId(),
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSellingStatus(),
                StockResponse.of(product.getStock())
        );
    }
}