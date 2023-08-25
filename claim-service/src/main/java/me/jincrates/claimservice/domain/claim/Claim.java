package me.jincrates.claimservice.domain.claim;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "CLAIM")
@Entity
public class Claim extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;          // 사용자 ID

    @Column(nullable = false)
    private Long orderId;         // 주문 ID

    private Long paymentId;       // 결제 ID

    private String claimNo;       // 접수번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimType type;       // 접수유형

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;   // 상태

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimReason reason;   // 접수사유
    @Lob
    @Column(nullable = false)
    private String memo;          // 상세사유

    @Lob
    @Column(nullable = false)
    private String rejectMemo;    // 반려사유

    @Column(nullable = false)
    private int deliveryFee;      // 배송비

    private String failMessage;   // 결제실패 사유

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    List<ClaimProduct> claimProducts = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Claim(Long orderId, Long paymentId, String claimNo, ClaimType type,
        ClaimStatus status, ClaimReason reason, String memo, String rejectMemo, int deliveryFee,
        String failMessage, List<OrderProduct> orderProducts) {
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.claimNo = claimNo;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.memo = memo;
        this.rejectMemo = rejectMemo;
        this.deliveryFee = deliveryFee;
        this.failMessage = failMessage;
        this.claimProducts = orderProducts.stream()
            .map(op -> ClaimProduct.builder()
                .claim(this)
                .orderProductId(op.getId())
                .quantity(op.getQuantity())  // 수량 받아와야함
                .refundPrice(op.getPrice())
                .status(status)
                .build()
            )
            .collect(Collectors.toList());
    }

    public static Claim create(Long orderId, ClaimType type, ClaimReason reason, ClaimStatus status,
        String memo, List<OrderProduct> orderProducts) {
        return Claim.builder()
            .orderId(orderId)
            .type(type)
            .status(status)
            .reason(reason)
            .memo(memo)
            .orderProducts(orderProducts)
            .build();
    }

    public void cancel() {
        if (!this.status.equals(ClaimStatus.REQUESTED)) {
            throw new IllegalArgumentException("철회는 접수 상태일 때만 가능합니다.");
        }

        this.status = ClaimStatus.CANCELED;
        this.claimProducts.forEach(ClaimProduct::cancel);
    }

    public void approve() {
        if (!this.status.equals(ClaimStatus.REQUESTED)) {
            throw new IllegalArgumentException("승인은 접수 상태일 때만 가능합니다.");
        }
        this.status = ClaimStatus.APPROVED;
        this.claimProducts.forEach(ClaimProduct::approve);
    }

    public void reject(String rejectMemo) {
        if (!this.status.equals(ClaimStatus.REQUESTED)) {
            throw new IllegalArgumentException("반려는 접수 상태일 때만 가능합니다.");
        }
        if (rejectMemo.isEmpty()) {
            throw new IllegalArgumentException("반려 사유를 입력하지 않았습니다.");
        }
        this.status = ClaimStatus.REJECTED;
        this.rejectMemo = rejectMemo;
        this.claimProducts.forEach(cp -> cp.reject(rejectMemo));
    }
}
