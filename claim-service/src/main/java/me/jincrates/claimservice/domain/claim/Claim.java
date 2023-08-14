package me.jincrates.claimservice.domain.claim;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.claimservice.domain.BaseEntity;
import me.jincrates.claimservice.domain.claimproduct.ClaimProduct;
import me.jincrates.claimservice.domain.orderproduct.OrderProduct;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "CLAIM")
public class Claim extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;         // 주문 ID
    private Long paymentId;       // 결제 ID
    @Enumerated(EnumType.STRING)
    private ClaimType type;       // 접수유형
    @Enumerated(EnumType.STRING)
    private ClaimStatus status;   // 상태
    @Enumerated(EnumType.STRING)
    private ClaimReason reason;   // 접수사유
    private String memo;          // 상세사유
    private String rejectMemo;    // 반려사유
    private int deliveryFee;      // 배송비
    private String invoiceNo;     // 운송장번호
    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    List<ClaimProduct> claimProducts = new ArrayList<>();

    @Builder
    private Claim(Long orderId, Long paymentId, ClaimType type, ClaimStatus status,
        ClaimReason reason, String memo, String rejectMemo, int deliveryFee,
        String invoiceNo,
        List<OrderProduct> orderProducts) {
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.memo = memo;
        this.rejectMemo = rejectMemo;
        this.deliveryFee = deliveryFee;
        this.invoiceNo = invoiceNo;
        this.claimProducts = orderProducts.stream()
            .map(op -> ClaimProduct.builder()
                .claim(this)
                .orderProductId(op.getId())
                .quantity(op.getQuantity())  // 수량 받아와야함
                .refundPrice(op.getPrice())
                .status(ClaimStatus.RECEIPT)
                .rejectMemo(reason.getDescription())
                .build()
            )
            .collect(Collectors.toList());
    }

    public static Claim create(Long orderId, ClaimType type, ClaimReason reason,
        String memo, List<OrderProduct> orderProducts) {
        return Claim.builder()
            .orderId(orderId)
            .type(type)
            .status(ClaimStatus.RECEIPT)  // 접수
            .reason(reason)
            .memo(memo)
            .rejectMemo(reason.getDescription())
            .orderProducts(orderProducts)
            .build();
    }
}
