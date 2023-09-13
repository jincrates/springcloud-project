package me.jincrates.api.ecommerce.products.api.service.request;

import lombok.Getter;
import me.jincrates.api.ecommerce.products.domain.product.ProductSellingStatus;

@Getter
public class ProductSearchServiceRequest {
    private String searchDateType;
    private ProductSellingStatus searchStatus;
    private String searchBy;
    private String searchQuery;
}
