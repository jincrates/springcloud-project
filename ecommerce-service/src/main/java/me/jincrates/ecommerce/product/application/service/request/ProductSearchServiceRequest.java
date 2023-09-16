package me.jincrates.ecommerce.product.application.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSearchServiceRequest {

    private String searchDateType;
    private ProductSellingStatus searchStatus;
    private String searchBy;
    private String searchQuery;
}
