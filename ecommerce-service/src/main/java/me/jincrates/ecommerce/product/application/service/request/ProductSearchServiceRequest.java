package me.jincrates.ecommerce.product.application.service.request;

import lombok.Getter;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

@Getter
public class ProductSearchServiceRequest {

    private String searchDateType;
    private ProductSellingStatus searchStatus;
    private String searchBy;
    private String searchQuery;
}
