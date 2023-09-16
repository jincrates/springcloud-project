package me.jincrates.ecommerce.product.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.domain.ProductImage;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductImageServiceResponse {

    private Long id;  // 상품 이미지 ID
    private String imageName;  // 이미지 파일명
    private String originImageName; // 원본 파일명
    private String imageUrl;  // 이미지 조회 경로
    private boolean represented;  // 대표 이미지 여부

    public ProductImageServiceResponse(Long id, String imageName, String originImageName, String imageUrl, boolean represented) {
        this.id = id;
        this.imageName = imageName;
        this.originImageName = originImageName;
        this.imageUrl = imageUrl;
        this.represented = represented;
    }

    public static ProductImageServiceResponse of(ProductImage entity) {
        return new ProductImageServiceResponse(
                entity.getId(),
                entity.getImageName(),
                entity.getOriginImageName(),
                entity.getImageUrl(),
                entity.isRepresented()
        );
    }
}
