package me.jincrates.orderservice.domain.claimproduct;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.orderservice.domain.BaseEntity;
import me.jincrates.orderservice.domain.delivery.DeliveryTypeCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "EXCHANGE_PRODUCT")
public class ExchangeProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private ClaimProduct claimProduct;  // 교환 접수 상품

    private Long productId;  // 출고할 상품 ID

    private Long exchangeDeliveryStatusId;  // 재배송지 배송상태 ID;

    @Enumerated(EnumType.STRING)
    private DeliveryTypeCode exchangeDeliveryTypeCode;  // 재배송지 배송유형

    private Integer quantity;  // 접수수량
}
