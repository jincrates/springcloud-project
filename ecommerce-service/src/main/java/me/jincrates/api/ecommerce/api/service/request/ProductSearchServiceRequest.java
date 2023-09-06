package me.jincrates.api.ecommerce.api.service.request;

import lombok.Getter;
import me.jincrates.api.ecommerce.domain.product.ProductSellingStatus;

@Getter
public class ProductSearchServiceRequest {
    private String searchDateType;
    private ProductSellingStatus searchStatus;
    private String searchBy;
    private String searchQuery;
}
