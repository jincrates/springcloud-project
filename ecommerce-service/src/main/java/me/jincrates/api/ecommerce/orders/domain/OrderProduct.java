package me.jincrates.api.ecommerce.orders.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.ecommerce.products.domain.product.Product;
import me.jincrates.api.global.common.BaseEntity;

@Getter
@Entity
@Table(name = "ORDER_PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;  // 주문상품 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", columnDefinition = "bigint")
    private Product product;

    private int orderPrice;  // 주문 가격

    private int quantity;  // 주문 수량

    @Builder(access = AccessLevel.PRIVATE)
    private OrderProduct(Order order, Product product, int orderPrice, int quantity) {
        this.order = order;
        this.product = product;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public static OrderProduct create(Product product, int quantity) {
        // 재고 감소
        return OrderProduct.builder()
            .product(product)
            .quantity(quantity)
            .orderPrice(product.getPrice() * quantity)
            .build();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void cancel() {
        //this.getProduct()
    }
}
