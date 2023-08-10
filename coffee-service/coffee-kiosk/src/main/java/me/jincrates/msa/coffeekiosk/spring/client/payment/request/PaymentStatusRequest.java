package me.jincrates.msa.coffeekiosk.spring.client.payment.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PayMethod;

@Getter
public class PaymentStatusRequest {

    private final PayMethod payMethod;
    private final String authNo;
    private final String uniqueId;
    private final LocalDateTime searchedAt;

    @Builder
    public PaymentStatusRequest(PayMethod payMethod, String authNo, String uniqueId,
        LocalDateTime searchedAt) {
        this.payMethod = payMethod;
        this.authNo = authNo;
        this.uniqueId = uniqueId;
        this.searchedAt = searchedAt;
    }
}
