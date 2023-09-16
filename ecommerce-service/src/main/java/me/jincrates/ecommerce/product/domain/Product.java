package me.jincrates.ecommerce.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.application.service.request.ProductUpdateRequest;
import me.jincrates.global.common.BaseEntity;

@Getter
@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;  // 상품 ID

    @Column(nullable = false, length = 50)
    private String productName;  // 상품명

    @Column(nullable = false)  // 가격
    private Integer price;

    @Lob
    @Column(nullable = false)
    private String productDetail;  // 상품 상세설명

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductSellingStatus status;  // 상품 판매상태

    @Builder(access = AccessLevel.PRIVATE)
    private Product(String productName, Integer price, String productDetail,
                    ProductSellingStatus status) {
        if (productName == null) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("상품 가격은 0원 이상이여야 합니다.");
        }

        if (productDetail == null) {
            throw new IllegalArgumentException("상품 상세설명은 필수입니다.");
        }

        if (status == null) {
            throw new IllegalArgumentException("상품 판매상태는 필수입니다.");
        }

        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
        this.status = status;
    }

    public static Product create(String productName, Integer price, String productDetail) {
        return Product.builder()
                .productName(productName)
                .price(price)
                .productDetail(productDetail)
                .status(ProductSellingStatus.HOLD)
                .build();
    }

    public void update(ProductUpdateRequest request) {
        this.id = request.productId();
        this.productName = request.productName();
        this.price = request.price();
        this.productDetail = request.productDetail();
        this.status = request.status();
    }
}
