package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.TossPayProperties;

@Getter
public class TossPayApproveRequest {

    private final String apiKey;    // *가맹점 key
    private final String payToken;  // *토스페이 토큰 (승인할 결제 건의 토큰값)
    private final String orderNo;   // 가맹점의 상품 주문번호: 결제 승인 시 orderNo를 함께 보내면 일치 여부를 확인합니다. payToken과 연결된 결제건과 orderNo가 다르면 승인 실패처리하여 다시 한번 유효성을 검증합니다.

    @Builder
    private TossPayApproveRequest(String payToken, String uniqueKey, TossPayProperties properties) {
        this.apiKey = properties.getApiKey();
        this.payToken = payToken;
        this.orderNo = uniqueKey;
    }

    @Override
    public String toString() {
        return "TossPayApproveRequest{" +
            ", payToken='" + payToken + '\'' +
            ", orderNo='" + orderNo + '\'' +
            '}';
    }

    public static TossPayApproveRequest of(PaymentApproveRequest request,
        TossPayProperties properties) {
        return TossPayApproveRequest.builder()
            .payToken(request.getAuthNo())
            .properties(properties)
            .build();
    }
}
