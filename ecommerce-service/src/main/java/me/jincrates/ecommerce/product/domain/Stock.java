package me.jincrates.ecommerce.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.global.common.BaseTimeEntity;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Comment("재고")
@Table(name = "stocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    @Comment("재고 ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @Comment("상품 ID")
    private Product product;

    @Column(nullable = false)
    @Comment("수량")
    private Integer quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("타입(IN: 입고, OUT: 출고)")
    private StockType type;

    @Version
    private int version;

    @Builder(access = AccessLevel.PRIVATE)
    private Stock(Product product, Integer quantity, StockType type) {
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.notNull(quantity, "재고 수량은 필수입니다.");
        Assert.isTrue(quantity < 0, "재고 수량은 0 이상이여야 합니다.");
        Assert.notNull(type, "재고 타입은 필수입니다.");

        this.product = product;
        this.quantity = quantity;
        this.type = type;
    }

    public static Stock create(Product product, Integer quantity, StockType type) {
        return Stock.builder()
                .product(product)
                .quantity(quantity)
                .type(type)
                .build();
    }

    public boolean isQuantityLessThan(Integer quantity) {
        return this.quantity < quantity;
    }

    public void deductQuantity(Integer quantity) {
        if (isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException("차감할 재고 수량이 없습니다.");
        }
        this.quantity -= quantity;
    }

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
    }
}
