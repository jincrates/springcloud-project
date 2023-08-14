package com.example.claim.domain.orderproduct;

import com.example.claim.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long productId;
    private Long productItemId;
    private int price;

    @Builder
    private OrderProduct(Long orderId, Long productId, Long productItemId, int price) {
        this.orderId = orderId;
        this.productId = productId;
        this.productItemId = productItemId;
        this.price = price;
    }
}
