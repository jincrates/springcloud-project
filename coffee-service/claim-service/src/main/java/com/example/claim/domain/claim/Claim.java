package com.example.claim.domain.claim;

import com.example.claim.domain.BaseEntity;
import com.example.claim.domain.claimorderproduct.ClaimOrderProduct;
import com.example.claim.domain.orderproduct.OrderProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "CLAIM")
public class Claim extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId; // 주문번호

    private String claimNo;  //접수번호

    @Enumerated(EnumType.STRING)
    private ClaimType type;  //접수유형

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;  //상태

    @Enumerated(EnumType.STRING)
    private ClaimReason reason;  //접수사유

    private String memo;  //상세사유

    private String invoiceNo;  //운송장 번호

    private Long paymentId; //결제 ID

    @OneToOne
    private PickupAddress pickupAddress; //수거지

    @OneToOne
    private RedeliveryAddress redeliveryAddress;  //재배송지

    private Long videoId;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    List<ClaimOrderProduct> claimOrderProducts = new ArrayList<>();

    @Builder
    private Claim(Long orderId, ClaimType type, ClaimReason reason, ClaimStatus status,
        String memo, String invoiceNo, List<OrderProduct> orderProducts) {
        this.orderId = orderId;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.memo = memo;
        this.invoiceNo = invoiceNo;
        this.claimOrderProducts = orderProducts.stream()
            .map(op -> ClaimOrderProduct.builder()
                .claim(this)
                .orderProductId(op.getId())
                .refundPrice(op.getPrice())
                .status(ClaimStatus.RECEIPT)
                .rejectMemo(reason.getDescription())
                .build()
            )
            .collect(Collectors.toList());
    }

    public static Claim create(Long orderId, ClaimType type, ClaimReason reason,
        String memo, String invoiceNo, List<OrderProduct> orderProducts) {
        return Claim.builder()
            .orderId(orderId)
            .type(type)
            .status(ClaimStatus.RECEIPT)
            .reason(reason)
            .memo(memo)
            .invoiceNo(invoiceNo)
            .orderProducts(orderProducts)
            .build();
    }

    public void approval() {
        this.status = ClaimStatus.APPROVAL;
    }
}
