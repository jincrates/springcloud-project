package me.jincrates.claimservice.api.controller.response;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.claimservice.domain.claim.ClaimStatus;
import me.jincrates.claimservice.domain.claimproduct.ClaimProduct;

@Getter
public class ClaimProductResponse {
    private Long id;
    private Long orderProductId;
    private int quantity;
    private int refundPrice;
    private ClaimStatus status;
    private String rejectMemo;
    private Long originProductId;
    private Long changeProductId;

    @Builder
    private ClaimProductResponse(Long id, Long orderProductId, int quantity, int refundPrice,
                                 ClaimStatus status, String rejectMemo, Long originProductId, Long changeProductId) {
        this.id = id;
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.refundPrice = refundPrice;
        this.status = status;
        this.rejectMemo = rejectMemo;
        this.originProductId = originProductId;
        this.changeProductId = changeProductId;
    }

    public static ClaimProductResponse of(ClaimProduct claimProduct) {
        return ClaimProductResponse.builder()
                .id(claimProduct.getId())
                .orderProductId(claimProduct.getOrderProductId())
                .quantity(claimProduct.getQuantity())
                .refundPrice(claimProduct.getRefundPrice())
                .status(claimProduct.getStatus())
                .rejectMemo(claimProduct.getRejectMemo())
                .orderProductId(claimProduct.getOrderProductId())
                .changeProductId(claimProduct.getChangeProductId())
                .build();
    }
}
