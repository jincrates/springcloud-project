package me.jincrates.api.claims.domain.claim.deliveryFee;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.claims.domain.claim.Claim;
import me.jincrates.api.claims.domain.delivery.DeliveryTypeCode;
import me.jincrates.global.common.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CLAIM_DELIVERY_FEE")
@Entity
public class ClaimDeliveryFee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long claimId;

    @Enumerated(EnumType.STRING)
    private DeliveryTypeCode deliveryTypeCode;

    private Integer deliveryFee;

    @Builder(access = AccessLevel.PRIVATE)
    private ClaimDeliveryFee(Long claimId, DeliveryTypeCode deliveryTypeCode,
                             Integer deliveryFee) {
        this.claimId = claimId;
        this.deliveryTypeCode = deliveryTypeCode;
        this.deliveryFee = deliveryFee;
    }

    public static ClaimDeliveryFee create(Claim claim, String deliveryTypeCode) {
        return ClaimDeliveryFee.builder()
                .claimId(claim.getId())
                .deliveryFee(claim.getDeliveryFee())
                .deliveryTypeCode(DeliveryTypeCode.valueOf(deliveryTypeCode))
                .build();
    }
}
