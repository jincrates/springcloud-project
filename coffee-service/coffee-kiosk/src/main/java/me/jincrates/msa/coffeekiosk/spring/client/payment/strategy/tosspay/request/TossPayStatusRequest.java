package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentStatusRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.TossPayProperties;

@Getter
public class TossPayStatusRequest {

    private final String apiKey;    // *가맹점 key
    private final String payToken;  // *토스페이 토큰 (승인할 결제 건의 토큰값)
    private final String orderNo;   // *토스페이 토큰 또는 가맹점 주문번호

    @Builder
    private TossPayStatusRequest(String payToken, String uniqueId, TossPayProperties properties) {
        this.apiKey = properties.getApiKey();
        this.payToken = payToken;
        this.orderNo = uniqueId;
    }

    @Override
    public String toString() {
        return "TossPayStatusRequest{" +
            "payToken='" + payToken + '\'' +
            ", orderNo='" + orderNo + '\'' +
            '}';
    }

    public static TossPayStatusRequest of(PaymentStatusRequest request,
        TossPayProperties properties) {
        return TossPayStatusRequest.builder()
            .payToken(request.getAuthNo())
            .uniqueId(request.getUniqueId())
            .properties(properties)
            .build();
    }
}
