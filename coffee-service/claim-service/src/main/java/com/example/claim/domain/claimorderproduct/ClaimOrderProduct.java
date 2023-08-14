package com.example.claim.domain.claimorderproduct;

import com.example.claim.domain.BaseEntity;
import com.example.claim.domain.claim.Claim;
import com.example.claim.domain.claim.ClaimStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "CLAIM_ORDER_PRODUCT")
public class ClaimOrderProduct extends BaseEntity {

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

    @Builder
    private ClaimOrderProduct(Claim claim, Long orderProductId, int quantity, int refundPrice,
        ClaimStatus status, String rejectMemo) {
        this.claim = claim;
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.refundPrice = refundPrice;
        this.status = status;
        this.rejectMemo = rejectMemo;
    }
}
