package me.jincrates.ecommerce.product.application.service.request;

public record DiscountRequest(
        DiscountType discountType,
        Double discountValue
) {
}
