package me.jincrates.claimservice.domain.claimproduct;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.claimservice.domain.BaseEntity;
import me.jincrates.claimservice.domain.claim.Claim;
import me.jincrates.claimservice.domain.claim.ClaimStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "CLAIM_PRODUCT")
public class ClaimProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Claim claim;

    private Long orderProductId;

    private int quantity;

    private int refundPrice;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    private String rejectMemo;

    private Long originProductId;

    private Long changeProductId;

    @Builder
    private ClaimProduct(Long id, Claim claim, Long orderProductId, int quantity, int refundPrice,
                         ClaimStatus status, Long originProductId, Long changeProductId) {
        this.id = id;
        this.claim = claim;
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.refundPrice = refundPrice;
        this.status = status;
        this.originProductId = originProductId;
        this.changeProductId = changeProductId;
    }

    public void withdrawal() {
        if (!this.status.equals(ClaimStatus.RECEIPT)) {
            throw new IllegalArgumentException("철회는 접수 상태일 때만 가능합니다.");
        }

        this.status = ClaimStatus.WITHDRAWAL;
    }

    public void approval() {
        if (!this.status.equals(ClaimStatus.RECEIPT)) {
            throw new IllegalArgumentException("승인은 접수 상태일 때만 가능합니다.");
        }
        this.status = ClaimStatus.APPROVAL;
    }

    public void reject(String rejectMemo) {
        if (!this.status.equals(ClaimStatus.RECEIPT)) {
            throw new IllegalArgumentException("반려는 접수 상태일 때만 가능합니다.");
        }
        if (rejectMemo.isEmpty()) {
            throw new IllegalArgumentException("반려 사유를 입력하지 않았습니다.");
        }
        this.status = ClaimStatus.REJECTION;
        this.rejectMemo = rejectMemo;
    }
}
