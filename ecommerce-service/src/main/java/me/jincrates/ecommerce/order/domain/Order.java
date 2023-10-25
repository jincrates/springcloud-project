package me.jincrates.ecommerce.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.global.common.BaseTimeEntity;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Comment("주문")
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @Column(name = "order_id")
    @Comment("주문 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("주문자 ID")
    private Member member;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("주문 상태")
    private OrderStatus orderStatus;

    @Column(nullable = false)
    @Comment("주문 총액")
    private Integer amount;

    @Comment("실패 메시지")
    private String failureMessage;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();  // 주문 상품 list

    @Builder(access = AccessLevel.PRIVATE)
    private Order(Long id, Member member, OrderStatus orderStatus, Integer amount, List<OrderItem> orderItems) {
        Assert.notNull(id, "주문 ID는 필수입니다.");
        Assert.notNull(member, "주문자 정보는 필수입니다.");
        Assert.notNull(orderStatus, "주문 상태는 필수입니다.");
        Assert.notNull(amount, "주문 총액은 필수입니다.");
        Assert.isTrue(amount < 0, "주문 총액은 0 이상이여야 합니다.");

        this.id = id;
        this.member = member;
        this.orderStatus = orderStatus;
        this.amount = amount;
        this.orderItems = orderItems;
    }

    public static Order create(Member member, List<OrderItem> orderItems) {
        Order order = Order.builder()
                .member(member)
                .orderStatus(OrderStatus.PENDING)
                .orderItems(orderItems)
                .amount(orderItems.stream()
                        .filter(orderItem -> orderItem.getOrderItemStatus() == OrderItemStatus.ORDERED)
                        .mapToInt(OrderItem::getOrderPrice).sum())
                .build();

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }

        return order;
    }

    public int calculateOrderAmount() {
        return orderItems.stream()
                .filter(orderItem -> OrderItemStatus.ORDERED.equals(orderItem.getOrderItemStatus()))
                .mapToInt(OrderItem::getOrderPrice).sum();
    }

    public void pay() {
        if (this.orderStatus != OrderStatus.PENDING) {
            throw new IllegalArgumentException("결제를 할 수 없는 주문 상태입니다.");
        }
        this.orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (this.orderStatus != OrderStatus.PAID) {
            throw new IllegalArgumentException("승인을 할 수 없는 주문 상태입니다.");
        }
        this.orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(String failureMessage) {
        if (this.orderStatus != OrderStatus.PAID) {
            throw new IllegalArgumentException("취소 초기화를 할 수 없는 주문 상태입니다.");
        }
        this.orderStatus = OrderStatus.CANCELLING;
        this.failureMessage = failureMessage;
        orderItems.forEach(OrderItem::cancel);
    }

    public void cancel(String failureMessage) {
        if (!(this.orderStatus == OrderStatus.CANCELLING || this.orderStatus == OrderStatus.PENDING)) {
            throw new IllegalArgumentException("취소를 할 수 없는 주문 상태입니다.");
        }
        this.orderStatus = OrderStatus.CANCELLED;
        this.failureMessage = failureMessage;
        orderItems.forEach(OrderItem::cancel);
    }
}
