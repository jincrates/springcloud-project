package me.jincrates.msa.coffeekiosk.spring.client.payment.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PayMethod;

@Getter
public class PaymentApproveRequest {

    private final PayMethod payMethod;
    private final String authNo;
    private final LocalDateTime approvedAt;

    @Builder
    private PaymentApproveRequest(PayMethod payMethod, String authNo, LocalDateTime approvedAt) {
        this.payMethod = payMethod;
        this.authNo = authNo;
        this.approvedAt = approvedAt;
    }
}
