package me.jincrates.msa.coffeekiosk.spring.client.payment.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PaymentMethod;

import java.time.LocalDateTime;

@Getter
public class PaymentApproveRequest {

    private final PaymentMethod paymentMethod;
    private final String authNo;
    private final LocalDateTime approvedAt;

    @Builder
    private PaymentApproveRequest(PaymentMethod paymentMethod, String authNo, LocalDateTime approvedAt) {
        this.paymentMethod = paymentMethod;
        this.authNo = authNo;
        this.approvedAt = approvedAt;
    }
}
