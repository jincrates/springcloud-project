package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.TossPayProperties;

@Getter
public class TossPayCancelRequest {

    private final String apiKey;    // *가맹점 key
    private final String payToken;  // *토스페이 토큰 (승인할 결제 건의 토큰값)
    private final String reason;    // 환불 사유

    @Builder
    private TossPayCancelRequest(String payToken, String reason, TossPayProperties properties) {
        this.apiKey = properties.getApiKey();
        this.payToken = payToken;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "TossPayCancelRequest{" +
            "apiKey='" + apiKey + '\'' +
            ", payToken='" + payToken + '\'' +
            ", reason='" + reason + '\'' +
            '}';
    }
}
