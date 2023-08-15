package me.jincrates.claimservice.api.controller.response;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.claimservice.domain.claim.Claim;
import me.jincrates.claimservice.domain.claim.ClaimReason;
import me.jincrates.claimservice.domain.claim.ClaimStatus;
import me.jincrates.claimservice.domain.claim.ClaimType;
import me.jincrates.claimservice.domain.claimproduct.ClaimProduct;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClaimResponse {
    private Long id;
    private Long orderId;         // 주문 ID
    private Long paymentId;       // 결제 ID
    private ClaimType type;       // 접수유형
    private ClaimStatus status;   // 상태
    private ClaimReason reason;   // 접수사유
    private String memo;          // 상세사유
    private String rejectMemo;    // 반려사유
    private int deliveryFee;      // 배송비
    private String invoiceNo;     // 운송장번호
    List<ClaimProduct> claimProducts = new ArrayList<>();

    @Builder
    private ClaimResponse(Long id, Long orderId, Long paymentId, ClaimType type, ClaimStatus status, ClaimReason reason, String memo, String rejectMemo, int deliveryFee, String invoiceNo, List<ClaimProduct> claimProducts) {
        this.id = id;
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.memo = memo;
        this.rejectMemo = rejectMemo;
        this.deliveryFee = deliveryFee;
        this.invoiceNo = invoiceNo;
        this.claimProducts = claimProducts;
    }

    public static ClaimResponse of(Claim claim) {
        return ClaimResponse.builder()
                .id(claim.getId())
                .orderId(claim.getOrderId())
                .paymentId(claim.getPaymentId())
                .type(claim.getType())
                .status(claim.getStatus())
                .reason(claim.getReason())
                .memo(claim.getMemo())
                .rejectMemo(claim.getRejectMemo())
                .deliveryFee(claim.getDeliveryFee())
                .invoiceNo(claim.getInvoiceNo())
                .claimProducts(
                        claim.getClaimProducts().stream()
                                .map(cp -> ClaimProduct.builder()
                                        .id(cp.getId())
                                        .orderProductId(cp.getOrderProductId())
                                        .quantity(cp.getQuantity())
                                        .status(cp.getStatus())
                                        .build())
                                .toList()
                )
                .build();
    }
}
