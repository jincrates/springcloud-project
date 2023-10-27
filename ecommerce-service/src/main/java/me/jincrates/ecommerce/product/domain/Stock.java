package me.jincrates.ecommerce.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.global.common.BaseEntity;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Comment("재고")
@Table(name = "stocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    @Comment("재고 ID")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "product_id")
    @Comment("상품 ID")
    private Product product;

    @Column(nullable = false)
    @Comment("재고 수량")
    private Integer quantity;

    @Version
    private int version;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockHistory> stockHistories = new ArrayList<>();  // 재고 히스토리 list

    @Builder(access = AccessLevel.PRIVATE)
    private Stock(Product product, Integer quantity) {
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.notNull(quantity, "재고 수량은 필수입니다.");
        Assert.isTrue(quantity >= 0, "재고 수량은 0 이상이여야 합니다.");

        this.product = product;
        this.quantity = quantity;
    }

    public static Stock create(Product product, Integer quantity) {
        Stock stock = Stock.builder()
                .product(product)
                .quantity(quantity)
                .build();
        StockHistory stockHistory = StockHistory.create(stock, quantity, StockType.IN);
        stock.setStockHistories(List.of(stockHistory));
        return stock;
    }

    public void setStockHistories(List<StockHistory> stockHistories) {
        this.stockHistories = stockHistories;
    }

    public boolean isQuantityLessThan(Integer quantity) {
        return this.quantity < quantity;
    }

    public void deductQuantity(Integer quantity) {
        if (isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException("차감할 재고 수량이 없습니다.");
        }
        // 재고 내역 저장
        StockHistory stockHistory = StockHistory.create(this, quantity, StockType.OUT);
        this.stockHistories.add(stockHistory);

        // 재고 감소
        this.quantity -= quantity;
    }

    public void addQuantity(Integer quantity) {
        // 재고 내역 저장
        StockHistory stockHistory = StockHistory.create(this, quantity, StockType.IN);
        this.stockHistories.add(stockHistory);

        // 재고 증가
        this.quantity += quantity;
    }
}
