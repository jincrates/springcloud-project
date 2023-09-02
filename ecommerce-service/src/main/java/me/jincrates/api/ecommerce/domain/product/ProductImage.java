package me.jincrates.api.ecommerce.domain.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.global.common.BaseEntity;

@Getter
@Entity
@Table(name = "PRODUCT_IMAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long id;  // 상품 이미지 ID

    private String imageName;  // 이미지 파일명

    private String originImageName; // 원본 파일명

    private String imageUrl;  // 이미지 조회 경로

    private boolean represented;  // 대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(Product product) {
        this.product = product;
        this.represented = false;
    }

    @Builder(access = AccessLevel.PRIVATE)
    private ProductImage(String imageName, String originImageName, String imageUrl, Product product) {
        this.imageName = imageName;
        this.originImageName = originImageName;
        this.imageUrl = imageUrl;
        this.represented = false;
        this.product = product;
    }

    public void updateProductImage(String originImageName, String imageName, String imageUrl) {
        this.originImageName = originImageName;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public void represented() {
        this.represented = true;
    }
}
