package me.jincrates.ecommerce.product.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.product.domain.Stock;

@Schema(description = "재고 response")
public record StockResponse(
        @Schema(description = "재고 ID")
        Long stockId,
        @Schema(description = "상품 ID")
        Long productId,
        @Schema(description = "재고 수량")
        Integer quantity
) {
    public static StockResponse of(Stock stock) {
        return new StockResponse(
                stock.getId(),
                stock.getProduct().getId(),
                stock.getQuantity()
        );
    }
}