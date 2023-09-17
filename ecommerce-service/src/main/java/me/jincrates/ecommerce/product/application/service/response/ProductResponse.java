package me.jincrates.ecommerce.product.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "상품 response")
public record ProductResponse(
        @Schema(description = "상품 ID", example = "1")
        Long id,
        @Schema(description = "상품명", example = "소크라테스의 변명")
        String productName,
        @Schema(description = "상품 가격", example = "10000")
        Integer price,
        @Schema(description = "상품 상세 설명", example = "《소크라테스의 변명》은 인류 역사상 가장 위대한 철학자인 소크라테스의 영혼의 책이다.")
        String productDetail,
        @Schema(description = "상품 판매상태", example = "SELLING")
        ProductSellingStatus status,

        @Schema(description = "상품 이미지")
        List<ProductImageResponse> productImages,
        List<Long> productImagesIds) {

    public ProductResponse {
        productImages = productImages == null ? new ArrayList<>() : productImages;
        productImagesIds = productImagesIds == null ? new ArrayList<>() : productImagesIds;
    }

    public static ProductResponse of(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStatus(),
                null,
                null
                //productImages.stream().map(ProductImageResponse::of).toList(),
                //productImages.stream().map(ProductImage::getId).toList()
        );
    }
}