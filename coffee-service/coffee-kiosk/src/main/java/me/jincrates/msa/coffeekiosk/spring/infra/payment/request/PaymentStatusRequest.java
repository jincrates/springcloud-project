package me.jincrates.msa.coffeekiosk.spring.infra.payment.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.payment.PayMethod;

import java.time.LocalDateTime;

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
