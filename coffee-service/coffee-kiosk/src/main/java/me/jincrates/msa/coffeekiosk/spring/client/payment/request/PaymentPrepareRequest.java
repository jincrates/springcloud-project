package me.jincrates.msa.coffeekiosk.spring.client.payment.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PaymentMethod;

import java.time.LocalDateTime;

@Getter
public class PaymentPrepareRequest {

    private final PaymentMethod paymentMethod;
    private final String uniqueKey;
    private final int price;
    private final String productName;
    private final String callbackUrl;
    private final String cancelUrl;
    private final LocalDateTime preparedAt;

    @Builder
    private PaymentPrepareRequest(String uniqueKey, int price, String productName, String callbackUrl,
                                  String cancelUrl, PaymentMethod paymentMethod, LocalDateTime preparedAt) {
        this.uniqueKey = uniqueKey;
        this.price = price;
        this.productName = productName;
        this.callbackUrl = callbackUrl;
        this.cancelUrl = cancelUrl;
        this.paymentMethod = paymentMethod;
        this.preparedAt = preparedAt;
    }
}
