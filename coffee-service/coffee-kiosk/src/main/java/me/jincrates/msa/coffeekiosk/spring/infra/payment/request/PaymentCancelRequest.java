package me.jincrates.msa.coffeekiosk.spring.infra.payment.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.payment.PayMethod;

import java.time.LocalDateTime;

@Getter
public class PaymentCancelRequest {

    private final PayMethod payMethod;
    private final String uniqueId;
    private final String transactionId;
    private final int cancelPrice;
    private final String reason;
    private final LocalDateTime canceledAt;

    @Builder
    private PaymentCancelRequest(PayMethod payMethod, String uniqueId, String transactionId,
                                 String reason, int cancelPrice, LocalDateTime canceledAt) {
        this.payMethod = payMethod;
        this.uniqueId = uniqueId;
        this.transactionId = transactionId;
        this.cancelPrice = cancelPrice;
        this.reason = reason;
        this.canceledAt = canceledAt;
    }
}
