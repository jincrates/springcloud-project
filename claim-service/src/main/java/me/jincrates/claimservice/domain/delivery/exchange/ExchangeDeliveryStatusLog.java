package me.jincrates.claimservice.domain.delivery.exchange;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.claimservice.domain.delivery.DeliveryStatus;
import org.springframework.data.annotation.CreatedDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COLLECTION_DELIVERY_STATUS")
public class ExchangeDeliveryStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long exchangeDeliveryStatusId;

    @Column(nullable = false)
    private Long claimId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    private String invoiceNo;    // 운송장 번호

    private String invoiceCode;  // 택배사 코드

    private LocalDateTime completedAt;  // 교환완료일시

    private LocalDateTime canceledAt;   // 교환취소일시

    @CreatedDate
    private LocalDateTime createdAt;
}
