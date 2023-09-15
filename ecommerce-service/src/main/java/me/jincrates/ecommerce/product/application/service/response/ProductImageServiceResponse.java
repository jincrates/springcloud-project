package me.jincrates.ecommerce.product.application.service.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.ecommerce.product.domain.ProductImage;

@Getter
public class ProductImageServiceResponse {

    private Long id;  // 상품 이미지 ID
    private String imageName;  // 이미지 파일명
    private String originImageName; // 원본 파일명
    private String imageUrl;  // 이미지 조회 경로
    private boolean represented;  // 대표 이미지 여부

    @Builder(access = AccessLevel.PRIVATE)
    private ProductImageServiceResponse(Long id, String imageName, String originImageName,
        String imageUrl, boolean represented) {
        this.id = id;
        this.imageName = imageName;
        this.originImageName = originImageName;
        this.imageUrl = imageUrl;
        this.represented = represented;
    }

    public static ProductImageServiceResponse of(ProductImage image) {
        return ProductImageServiceResponse.builder()
            .id(image.getId())
            .imageName(image.getImageName())
            .originImageName(image.getOriginImageName())
            .imageUrl(image.getImageUrl())
            .represented(image.isRepresented())
            .build();
    }
}
