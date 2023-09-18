package me.jincrates.ecommerce.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    private String name;  // 상품명

    @Column(nullable = false)  // 가격
    private Integer price;

    @Lob
    @Column(nullable = false)
    private String description;  // 상품 상세설명

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductSellingStatus status;  // 상품 판매상태

    @OneToOne(mappedBy = "product")
    private Discount discount;

    @OneToOne(mappedBy = "product")
    private Stock stock;

    @Builder(access = AccessLevel.PRIVATE)
    private Product(String name, Integer price, String description, ProductSellingStatus status) {
        if (name == null) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("상품 가격은 0원 이상이여야 합니다.");
        }

        if (description == null) {
            throw new IllegalArgumentException("상품 상세설명은 필수입니다.");
        }

        if (status == null) {
            throw new IllegalArgumentException("상품 판매상태는 필수입니다.");
        }

        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public static Product create(String name, Integer price, String description) {
        return Product.builder()
            .name(name)
            .price(price)
            .description(description)
            .status(ProductSellingStatus.HOLD)
            .build();
    }

    public void update(ProductUpdateRequest request) {
        this.id = request.productId();
        this.name = request.productName();
        this.price = request.price();
        this.description = request.productDetail();
        this.status = request.status();
    }

    public void holding() {
        this.status = ProductSellingStatus.HOLD;
    }

    public void selling() {
        this.status = ProductSellingStatus.SELLING;
    }

    public void stopSelling() {
        this.status = ProductSellingStatus.STOP_SELLING;
    }

    public Integer getDiscountPrice() {
        if (discount == null) {
            return 0;
        }

        return discount.applyDiscount();
    }
}
