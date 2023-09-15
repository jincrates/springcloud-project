package me.jincrates.api.claims.domain.delivery.collection;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.claims.domain.delivery.DeliveryStatus;
import me.jincrates.global.common.BaseEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COLLECTION_DELIVERY_STATUS")
public class CollectionDeliveryStatus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long claimId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    private String invoiceNo;    // 운송장 번호

    private String invoiceCode;  // 택배사 코드

    private LocalDateTime completedAt;  // 수거완료일시

    private LocalDateTime canceledAt;   // 수거취소일시
}
