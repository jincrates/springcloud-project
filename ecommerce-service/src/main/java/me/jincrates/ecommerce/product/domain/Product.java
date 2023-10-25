package me.jincrates.ecommerce.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.store.domain.Store;
import me.jincrates.global.common.BaseEntity;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Comment("상품")
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @Comment("상품 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @Comment("상점 ID")
    private Store store;

    @Column(nullable = false, length = 50)
    @Comment("상품명")
    private String name;

    @Lob
    @Column(nullable = false)
    @Comment("상품 설명")
    private String description;

    @Column(nullable = false)
    @Comment("상품 가격")
    private Integer price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("판매 상태")
    private ProductSellingStatus sellingStatus;

    @Column(nullable = false)
    @Comment("재고 수량")
    private Integer stockQuantity;

    @Builder(access = AccessLevel.PRIVATE)
    private Product(Store store, String name, String description, Integer price, ProductSellingStatus sellingStatus, Integer stockQuantity) {
        Assert.notNull(name, "상품명은 필수입니다.");
        Assert.notNull(description, "상품 설명은 필수입니다.");
        Assert.notNull(price, "상품 가격은 필수입니다.");
        Assert.isTrue(price < 0, "상품 가격은 0원 이상이여야 합니다.");
        Assert.notNull(sellingStatus, "판매 상태는 필수입니다.");
        Assert.notNull(stockQuantity, "재고 수량은 필수입니다.");

        this.store = store;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sellingStatus = sellingStatus;
        this.stockQuantity = stockQuantity;
    }

    public static Product create(Store store, String name, String description, Integer price, ProductSellingStatus sellingStatus, Integer stockQuantity) {
        return Product.builder()
                .store(store)
                .name(name)
                .description(description)
                .price(price)
                .sellingStatus(sellingStatus)
                .stockQuantity(stockQuantity)
                .build();
    }

    public void holding() {
        this.sellingStatus = ProductSellingStatus.HOLD;
    }

    public void selling() {
        this.sellingStatus = ProductSellingStatus.SELLING;
    }

    public void stopSelling() {
        this.sellingStatus = ProductSellingStatus.STOP_SELLING;
    }
}
