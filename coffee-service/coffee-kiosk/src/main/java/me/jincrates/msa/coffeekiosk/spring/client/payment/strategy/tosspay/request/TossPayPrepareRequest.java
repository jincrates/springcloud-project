package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TossPayPrepareRequest {

    private final String apiKey;  //가맹점 key
    private final String orderNo; // 가맹점의 상품 주문번호
    private final int amount; //총 결제 금액
    private final int amountTaxFree;  //결제 금액 중 비과세금액
    private final String productDesc;  //상품 설명
    private final boolean autoExecute;  //자동 승인 여부 설정
    private final String retAppScheme;  //결제 완료 후 연결할 가맹점 측 앱 스킴 값
    private final String retUrl;  //구매자 인증완료 후 연결할 가맹점 웹페이지 URL
    private final String retCancelUrl;   //토스페이창에 진입한 사용자가 결제를 중단할때 사용자를 이동시킬 가맹점 취소 페이지

    @Builder
    private TossPayPrepareRequest(String uniqueKey, int price, String productName,
        String callbackUrl, String cancelUrl, String retAppScheme) {
        this.apiKey = "sk_test_mgkXl3jBk5mgkX1AnB25";
        this.orderNo = uniqueKey;
        this.amount = price;
        this.amountTaxFree = 0;
        this.productDesc = productName;
        this.autoExecute = Boolean.FALSE;
        this.retAppScheme = retAppScheme;
        this.retUrl = callbackUrl;
        this.retCancelUrl = cancelUrl;
    }
}
