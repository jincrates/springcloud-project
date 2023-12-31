package me.jincrates.msa.coffeekiosk.spring.temp.api.service.product.response;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.product.Product;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.product.ProductSellingStatus;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.product.ProductType;

@Getter
public class ProductResponse {

    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

    // buillder는 private로
    @Builder
    private ProductResponse(Long id, String productNumber, ProductType type,
                            ProductSellingStatus sellingStatus, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .type(product.getType())
                .sellingStatus(product.getSellingStatus())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
