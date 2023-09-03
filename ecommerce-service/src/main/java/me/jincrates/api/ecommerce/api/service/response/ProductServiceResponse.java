package me.jincrates.api.ecommerce.api.service.response;

import lombok.Getter;
import me.jincrates.api.ecommerce.domain.product.ProductSellingStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductServiceResponse {
    private Long id;  // 상품 ID
    private String productName;  // 상품명
    private int price;
    private String productDetail;  // 상품 상세설명
    private ProductSellingStatus status;  // 상품 판매상태
    private List<ProductImageServiceResponse> productImages = new ArrayList<>();
    private List<Long> productImagesIds = new ArrayList<>();

    public ProductServiceResponse(Long id, String productName, int price, String productDetail, ProductSellingStatus status, List<ProductImageServiceResponse> productImages, List<Long> productImagesIds) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.status = status;
        this.productImages = productImages;
        this.productImagesIds = productImagesIds;
    }
}
