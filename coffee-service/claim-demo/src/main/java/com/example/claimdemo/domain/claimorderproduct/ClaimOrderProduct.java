package com.example.claimdemo.domain.claimorderproduct;

import com.example.claimdemo.domain.BaseEntity;
import com.example.claimdemo.domain.claim.Claim;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ClaimOrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Claim claim;

    @Column(name = "order_product_id")
    private Long orderProductId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "refund_price")
    private int refundPrice;

    @Enumerated(EnumType.STRING)
    private ClaimProductStatus status;

    @Column(name = "reject_memo")
    private String rejectMemo;
}
