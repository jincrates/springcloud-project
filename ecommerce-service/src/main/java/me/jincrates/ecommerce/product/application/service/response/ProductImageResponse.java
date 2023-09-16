package me.jincrates.ecommerce.product.application.service.response;

import me.jincrates.ecommerce.product.domain.ProductImage;

public record ProductImageResponse(
        Long id,                 // 상품 이미지 ID
        String imageName,        // 이미지 파일명
        String originImageName,  // 원본 파일명
        String imageUrl,         // 이미지 조회 경로
        Boolean represented      // 대표 이미지 여부
) {

    public static ProductImageResponse of(ProductImage entity) {
        return new ProductImageResponse(
                entity.getId(),
                entity.getImageName(),
                entity.getOriginImageName(),
                entity.getImageUrl(),
                entity.getRepresented()
        );
    }
}
