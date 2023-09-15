package me.jincrates.api.claims.domain.claim.history;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.claims.domain.claim.Claim;
import me.jincrates.api.claims.domain.claim.ClaimStatus;
import me.jincrates.global.common.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CLAIM_HISTORY")
@Entity
public class ClaimHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;          // 사용자 ID

    @Column(nullable = false)
    private Long orderId;         // 주문 ID

    private Long claimId;         // 클레임

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;   // 상태

    @Builder(access = AccessLevel.PRIVATE)
    private ClaimHistory(Long userId, Long orderId, Long claimId, ClaimStatus status) {
        this.userId = userId;
        this.orderId = orderId;
        this.claimId = claimId;
        this.status = status;
    }

    public static ClaimHistory create(Claim claim) {
        return ClaimHistory.builder()
                .userId(claim.getUserId())
                .orderId(claim.getOrderId())
                .claimId(claim.getId())
                .status(claim.getStatus())
                .build();
    }
}
