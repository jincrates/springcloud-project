package me.jincrates.ecommerce.product.application.service.request;

import me.jincrates.ecommerce.product.domain.DiscountType;

public record DiscountRequest(
        DiscountType discountType,
        Double discountValue
) {
}
