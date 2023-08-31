package me.jincrates.msa.coffeekiosk.spring.temp.domain.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.global.common.BaseEntity;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.orderprodct.OrderProduct;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;

    private LocalDateTime registeredAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    private Order(OrderStatus orderStatus, List<Product> products, LocalDateTime registeredAt) {
        this.orderStatus = orderStatus;
        this.totalPrice = calculateTotalProduct(products);
        this.registeredAt = registeredAt;
        this.orderProducts = products.stream()
                .map(product -> new OrderProduct(this, product))
                .toList();
    }

    public static Order create(List<Product> products, LocalDateTime registeredAt) {
        return Order.builder()
                .orderStatus(OrderStatus.INIT)
                .products(products)
                .registeredAt(registeredAt)
                .build();
    }

    private int calculateTotalProduct(List<Product> products) {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }
}
