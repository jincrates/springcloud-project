package me.jincrates.ecommerce.payment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.order.domain.Order;
import me.jincrates.global.common.BaseEntity;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Comment("결제")
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    @Comment("결제 ID")
    private Long id;

    @Column(nullable = false)
    @Comment("주문 ID")
    private Long orderId;

    @Column(nullable = false)
    @Comment("결제자 ID")
    private Long memberId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("결제 수단")
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("결제 상태")
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    @Comment("결제 금액")
    private Integer paymentPrice;

    @Builder(access = AccessLevel.PRIVATE)
    private Payment(Long orderId, Long memberId, PaymentMethod paymentMethod, PaymentStatus paymentStatus, Integer paymentPrice) {
        Assert.notNull(orderId, "주문 ID는 필수입니다.");
        Assert.notNull(memberId, "주문자 ID는 필수입니다.");
        Assert.notNull(paymentMethod, "결제 수단은 필수입니다.");
        Assert.notNull(paymentStatus, "결제 상태는 필수입니다.");
        Assert.notNull(paymentPrice, "결제 금액은 필수입니다.");
        Assert.isTrue(paymentPrice < 0, "결제 금액은 0원 이상이여야합니다.");

        this.orderId = orderId;
        this.memberId = memberId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentPrice = paymentPrice;
    }

    public static Payment create(Order order, PaymentMethod paymentMethod) {
        return Payment.builder()
                .orderId(order.getId())
                .memberId(order.getMember().getId())
                .paymentPrice(order.getAmount())
                .paymentMethod(paymentMethod)
                .paymentStatus(PaymentStatus.COMPLETED)
                .build();
    }
}
