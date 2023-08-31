package me.jincrates.orderservice.domain.delivery.collection;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.orderservice.domain.delivery.DeliveryStatus;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COLLECTION_DELIVERY_STATUS_LOG")
public class CollectionDeliveryStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long collectionDeliveryStatusId;

    @Column(nullable = false)
    private Long claimId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    private String invoiceNo;    // 운송장 번호

    private String invoiceCode;  // 택배사 코드

    private LocalDateTime completedAt;  // 수거완료일시

    private LocalDateTime canceledAt;   // 수거취소일시

    @CreatedDate
    private LocalDateTime createdAt;
}
