package me.jincrates.msa.coffeekiosk.spring.infra.payment.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.payment.PayMethod;

import java.time.LocalDateTime;

@Getter
public class PaymentPrepareRequest {

    private final PayMethod payMethod;
    private final String uniqueId;
    private final int price;
    private final String productName;
    private final String callbackUrl;
    private final String cancelUrl;
    private final LocalDateTime preparedAt;

    @Builder
    private PaymentPrepareRequest(String uniqueId, int price, String productName,
                                  String callbackUrl,
                                  String cancelUrl, PayMethod payMethod, LocalDateTime preparedAt) {
        this.uniqueId = uniqueId;
        this.price = price;
        this.productName = productName;
        this.callbackUrl = callbackUrl;
        this.cancelUrl = cancelUrl;
        this.payMethod = payMethod;
        this.preparedAt = preparedAt;
    }
}
