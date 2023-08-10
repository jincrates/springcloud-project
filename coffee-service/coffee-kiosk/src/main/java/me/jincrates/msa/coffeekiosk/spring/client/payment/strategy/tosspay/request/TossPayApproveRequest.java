package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TossPayApproveRequest {

    private final String apiKey;    // *가맹점 key
    private final String payToken;  // *토스페이 토큰 (승인할 결제 건의 토큰값)
    private final String orderNo;   // 가맹점의 상품 주문번호: 결제 승인 시 orderNo를 함께 보내면 일치 여부를 확인합니다. payToken과 연결된 결제건과 orderNo가 다르면 승인 실패처리하여 다시 한번 유효성을 검증합니다.

    @Builder
    private TossPayApproveRequest(String payToken, String uniqueKey) {
        this.apiKey = "sk_test_mgkXl3jBk5mgkX1AnB25";
        this.payToken = payToken;
        this.orderNo = uniqueKey;
    }
}
