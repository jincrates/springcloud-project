package me.jincrates.msa.coffeekiosk.spring.client.payment.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PayMethod;

@Getter
public class PaymentStatusRequest {

    private final PayMethod payMethod;
    private final String uniqueId;
    private final String transactionId;
    private final int cancelPrice;
    private final LocalDateTime canceledAt;

    @Builder
    private PaymentStatusRequest(PayMethod payMethod, String uniqueId, String transactionId,
        int cancelPrice, LocalDateTime canceledAt) {
        this.payMethod = payMethod;
        this.uniqueId = uniqueId;
        this.transactionId = transactionId;
        this.cancelPrice = cancelPrice;
        this.canceledAt = canceledAt;
    }
}
