package me.jincrates.ecommerce.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.global.common.BaseTimeEntity;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Comment("주문 항목")
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    @Comment("주문 항목 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Comment("주문 ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @Comment("상품 ID")
    private Product product;

    @Column(nullable = false)
    @Comment("주문 수량")
    private Integer orderQuantity;

    @Column(nullable = false)
    @Comment("주문 금액")
    private Integer orderPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("주문 항목 상태")
    private OrderItemStatus orderItemStatus;

    @Builder(access = AccessLevel.PRIVATE)
    private OrderItem(Order order, Product product, Integer orderQuantity, Integer orderPrice, OrderItemStatus orderItemStatus) {
        Assert.notNull(order, "주문 정보는 필수입니다.");
        Assert.notNull(product, "상품 정보는 필수입니다.");
        Assert.notNull(orderQuantity, "주문 수량은 필수입니다.");
        Assert.isTrue(orderQuantity <= 0, "주문 수량은 0보다 커야합니다.");
        Assert.notNull(orderPrice, "주문 금액은 필수입니다.");
        Assert.isTrue(orderPrice <= 0, "주문 금액은 0 이상이여야합니다.");
        Assert.notNull(orderItemStatus, "주문 항목 상태는 필수입니다.");

        this.order = order;
        this.product = product;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.orderItemStatus = orderItemStatus;
    }

    public static OrderItem create(Product product, Integer orderQuantity) {
        return OrderItem.builder()
                .product(product)
                .orderQuantity(orderQuantity)
                .orderPrice(product.getPrice() * orderQuantity)
                .orderItemStatus(OrderItemStatus.ORDERED)
                .build();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void cancel() {
        this.orderItemStatus = OrderItemStatus.CANCELLED;
    }
}
