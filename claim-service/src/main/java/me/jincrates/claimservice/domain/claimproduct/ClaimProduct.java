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
                         ClaimStatus status, String rejectMemo, Long originProductId, Long changeProductId) {
        this.id = id;
        this.claim = claim;
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.refundPrice = refundPrice;
        this.status = status;
        this.rejectMemo = rejectMemo;
        this.originProductId = originProductId;
        this.changeProductId = changeProductId;
    }
}
