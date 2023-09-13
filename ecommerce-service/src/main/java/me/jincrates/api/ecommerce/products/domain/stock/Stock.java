package me.jincrates.api.ecommerce.products.domain.stock;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.ecommerce.products.domain.product.Product;
import me.jincrates.api.global.common.BaseEntity;

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

    private int quantity;

    @Builder(access = AccessLevel.PRIVATE)
    private Stock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static Stock create(Product product, int quantity) {
        return Stock.builder()
            .product(product)
            .quantity(quantity)
            .build();
    }

    public boolean isQuantityLessThan(int quantity) {
        return this.quantity < quantity;
    }

    public void deductQuantity(int quantity) {
        if (isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException("차감할 재고 수량이 없습니다.");
        }
        this.quantity -= quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
