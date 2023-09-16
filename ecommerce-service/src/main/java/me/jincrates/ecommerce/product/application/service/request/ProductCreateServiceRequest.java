package me.jincrates.ecommerce.product.application.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.adapter.web.request.ProductCreateRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductCreateServiceRequest {

    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private int quantity; // 재고 수량

    public ProductCreateServiceRequest(String productName, int price, String productDetail, int quantity) {
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.quantity = quantity;
    }

    public static ProductCreateServiceRequest of(ProductCreateRequest request) {
        return new ProductCreateServiceRequest(
                request.getProductName(),
                request.getPrice(),
                request.getProductDetail(),
                request.getQuantity()
        );
    }
}
