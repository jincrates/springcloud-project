package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TossPayApproveRequest {

    private final String apiKey;
    private final String payToken;

    @Builder
    private TossPayApproveRequest(String payToken) {
        this.apiKey = "sk_test_mgkXl3jBk5mgkX1AnB25";
        this.payToken = payToken;
    }
}
