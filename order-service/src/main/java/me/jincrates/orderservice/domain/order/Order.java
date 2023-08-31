package me.jincrates.orderservice.domain.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.orderservice.domain.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDERS")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;          // 사용자 ID

    private Long paymentId;       // 결제 ID

    @Builder
    private Order(Long userId, Long paymentId) {
        this.userId = userId;
        this.paymentId = paymentId;
    }
}
