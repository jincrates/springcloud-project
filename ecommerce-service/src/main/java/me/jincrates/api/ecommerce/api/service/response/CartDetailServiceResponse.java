package me.jincrates.api.ecommerce.api.service.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartDetailServiceResponse {

    private Long cartProductId;  // 장바구니 상품 아이디
    private String productName;  // 상품명
    private int price; // 상품 금액
    private int quantity;  // 수량
    private String imageUrl;  // 상품 이미지 경로

    @Builder
    @QueryProjection
    public CartDetailServiceResponse(Long cartProductId, String productName, int price,
        int quantity, String imageUrl) {
        this.cartProductId = cartProductId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }
}
