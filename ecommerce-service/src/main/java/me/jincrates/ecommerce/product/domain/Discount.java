package me.jincrates.ecommerce.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.global.common.BaseEntity;

@Getter
@Entity
@Table(name = "DISCOUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Discount extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long id;  // 할인 ID

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private Double discountValue;

    private Discount(Product product, DiscountType discountType, Double discountValue) {
        this.product = product;
        this.discountType = discountType;
        this.discountValue = discountValue;
    }

    public static Discount create(Product product, DiscountType discountType, Double discountValue) {
        return new Discount(product, discountType, discountValue);
    }
}
