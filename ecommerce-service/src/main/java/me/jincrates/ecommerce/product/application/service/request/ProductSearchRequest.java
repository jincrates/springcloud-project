package me.jincrates.ecommerce.product.application.service.request;

import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

public record ProductSearchRequest(
        String searchDateType,
        ProductSellingStatus searchStatus,
        String searchBy,
        String searchQuery
) {
    
}
