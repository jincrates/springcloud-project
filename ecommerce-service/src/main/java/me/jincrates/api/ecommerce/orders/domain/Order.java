package me.jincrates.api.ecommerce.orders.domain;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.ecommerce.members.domain.Member;
import me.jincrates.api.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @Column(name = "order_id")
    private UUID id;  // 주문 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;  // 주문자

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();  // 주문 상품 list

    private String failMessage;

    @QueryProjection
    @Builder(access = AccessLevel.PRIVATE)
    private Order(UUID id, Member member, OrderStatus orderStatus, List<OrderProduct> orderProducts) {
        this.id = id;
        this.member = member;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }

    public static Order create(Member member, List<OrderProduct> orderProducts) {
        Order order = Order.builder()
            .id(UUID.randomUUID())  // 주문번호 채번
            .member(member)
            .orderStatus(OrderStatus.RECEIPT)
            .orderProducts(orderProducts)
            .build();

        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrder(order);
        }

        return order;
    }

    public int calculateTotalProduct() {
        return orderProducts.stream().mapToInt(OrderProduct::getOrderPrice).sum();
    }

    public void progress() {
        this.orderStatus = OrderStatus.PROGRESS;
    }

    public void success() {
        this.orderStatus = OrderStatus.SUCCESS;
    }

    public void reject(String failMessage) {
        this.orderStatus = OrderStatus.REJECT;
        this.failMessage = failMessage;
    }

    public void cancel() {
        this.orderStatus = OrderStatus.CANCEL;
    }
}
