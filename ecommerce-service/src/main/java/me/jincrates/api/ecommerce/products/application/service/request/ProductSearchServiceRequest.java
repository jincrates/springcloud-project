package me.jincrates.api.ecommerce.products.application.service.request;

import lombok.Getter;
import me.jincrates.api.ecommerce.products.domain.ProductSellingStatus;

@Getter
public class ProductSearchServiceRequest {
    private String searchDateType;
    private ProductSellingStatus searchStatus;
    private String searchBy;
    private String searchQuery;
}
