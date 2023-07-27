package com.example.claimdemo.domain.claim;

import com.example.claimdemo.domain.BaseEntity;
import com.example.claimdemo.domain.claimorderproduct.ClaimOrderProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Claim extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId; // 주문번호

    @Column(name = "claim_no")
    private String claimNo;  //접수번호

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClaimType type;  //접수유형

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClaimStatus status;  //상태

    @Column(name = "reason", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClaimReason reason;  //접수사유

    @Column(name = "memo")
    private String memo;  //상세사유

    @Column(name = "invoice_no")
    private String invoiceNo;  //운송장 번호

    @Column(name = "payment_id")
    private Long paymentId; //결제 ID

    @Column(name = "pick_up_address_id")
    private Long pickUpAddressId; //수거지 ID

    @Column(name = "claim_delivery_address_id")
    private Long claimDeliveryAddressId; //재배송지 ID

    @Column(name = "videoId")
    private Long videoId;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    List<ClaimOrderProduct> claimOrderProducts = new ArrayList<>();

    private Claim(Long orderId, ClaimType type, ClaimReason reason,
        String memo, String invoiceNo, List<ClaimOrderProduct> claimOrderProducts) {
        this.orderId = orderId;
        this.type = type;
        this.status = ClaimStatus.RECEIPT;
        this.reason = reason;
        this.memo = memo;
        this.invoiceNo = invoiceNo;
        this.claimOrderProducts = claimOrderProducts;
    }

    public static Claim create(Long orderId, ClaimType type, ClaimReason reason,
        String memo, String invoiceNo, List<ClaimOrderProduct> claimOrderProducts) {

        return new Claim(orderId, type, reason, memo, invoiceNo, claimOrderProducts);
    }

    public void approval() {
        this.status = ClaimStatus.APPROVAL;
    }
}
