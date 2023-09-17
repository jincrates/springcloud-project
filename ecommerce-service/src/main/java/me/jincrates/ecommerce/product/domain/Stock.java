package me.jincrates.ecommerce.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.global.common.BaseEntity;

@Getter
@Entity
@Table(name = "STOCK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Version
    private int version;

    @Builder(access = AccessLevel.PRIVATE)
    private Stock(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static Stock create(Product product, Integer quantity) {
        return Stock.builder()
                .product(product)
                .quantity(quantity)
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
