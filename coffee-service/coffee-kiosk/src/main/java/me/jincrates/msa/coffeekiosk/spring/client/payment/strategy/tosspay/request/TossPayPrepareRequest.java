package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.TossPayProperties;

@Getter
public class TossPayPrepareRequest {

    private final String apiKey;                  // *가맹점 key
    private final String orderNo;                 // *가맹점의 상품 주문번호
    private final int amount;                     // *총 결제 금액
    private final int amountTaxFree;              // *결제 금액 중 비과세금액
    private final String productDesc;             // *상품 설명
    private final String retUrl;                  // *구매자 인증완료 후 연결할 가맹점 웹페이지 URL
    private final String retCancelUrl;            // *토스페이창에 진입한 사용자가 결제를 중단할때 사용자를 이동시킬 가맹점 취소 페이지
    private final Boolean autoExecute;            // 자동 승인 여부 설정
    private final String retAppScheme;            // 결제 완료 후 연결할 가맹점 측 앱 스킴 값

//    private final String resultCallback;          // 결제 결과 callback URL(autoExecute를 true로 사용하시는 경우에만 필수 값)
//    private final String callbackVersion;         // callbackVersion(autoExecute를 true로 사용하시는 경우에만 필수 값)
//    private final Integer amountTaxable;          // 결제 금액 중 과세금액
//    private final Integer amountVat;              // 결제 금액 중 부가세
//    private final Integer amountServiceFee;       // 결제 금액 중 봉사료
//    private final String expiredTime;             // 결제 만료 기한 (기본값 10분, 최대 60분 설정 가능) - 형식: 2020-03-03 12:30:20
//    private final String enablePayMethods;        // 결제수단 구분변수
//    private final Boolean cashReceipt;            // 현금영수증 발급 가능 여부(기본값: true)
//    private final String cashReceiptTradeOption;  // 현금영수증 발급타입(CULTURE: 문화비, GENERAL: 일반(default), PUBLIC_TP: 교통비)
//    private final String cardOptions;             // 결제창에 특정 카드만 노출하고 싶다면, options 변수에 토스 카드코드를 추가해 주세요. 예를들어, 삼성카드와 현대카드만 결제가 가능하도록 노출을 제어한다면 아래와 같이 토스 카드코드를 넘겨주시면 됩니다. "cardOptions": {"options": [{"cardCompanyCode":3},{"cardCompanyCode":5}]}
//    private final String installment;             // 할부 제한 타입(USE: 할부 사용(default), NOT_USE: 할부 미사용)

    @Builder
    private TossPayPrepareRequest(String uniqueId, int price, String productName,
        String callbackUrl, String cancelUrl, boolean autoExecute, String retAppScheme,
        TossPayProperties properties
//        String resultCallback, String callbackVersion, int amountTaxable, Integer amountVat,
//        Integer amountServiceFee, String expiredTime, String enablePayMethods, boolean cashReceipt,
//        String cashReceiptTradeOption, String cardOptions, String installment
    ) {
        this.apiKey = properties.getApiKey();
        this.orderNo = uniqueId;
        this.amount = price;
        this.amountTaxFree = 0;
        this.productDesc = productName;
        this.retUrl = callbackUrl;
        this.retCancelUrl = cancelUrl;
        this.autoExecute = autoExecute;
        this.retAppScheme = retAppScheme;

//        this.resultCallback = resultCallback;
//        this.callbackVersion = callbackVersion;
//        this.amountTaxable = amountTaxable;
//        this.amountVat = amountVat;
//        this.amountServiceFee = amountServiceFee;
//        this.expiredTime = expiredTime;
//        this.enablePayMethods = enablePayMethods;
//        this.cashReceipt = cashReceipt;
//        this.cashReceiptTradeOption = cashReceiptTradeOption;
//        this.cardOptions = cardOptions;
//        this.installment = installment;
    }

    @Override
    public String toString() {
        return "TossPayPrepareRequest{" +
            "apiKey='" + apiKey + '\'' +
            ", orderNo='" + orderNo + '\'' +
            ", amount=" + amount +
            ", amountTaxFree=" + amountTaxFree +
            ", productDesc='" + productDesc + '\'' +
            ", retUrl='" + retUrl + '\'' +
            ", retCancelUrl='" + retCancelUrl + '\'' +
            ", autoExecute=" + autoExecute +
            ", retAppScheme='" + retAppScheme + '\'' +
//            ", resultCallback='" + resultCallback + '\'' +
//            ", callbackVersion='" + callbackVersion + '\'' +
//            ", amountTaxable=" + amountTaxable +
//            ", amountVat=" + amountVat +
//            ", amountServiceFee=" + amountServiceFee +
//            ", expiredTime='" + expiredTime + '\'' +
//            ", enablePayMethods='" + enablePayMethods + '\'' +
//            ", cashReceipt=" + cashReceipt +
//            ", cashReceiptTradeOption='" + cashReceiptTradeOption + '\'' +
//            ", cardOptions='" + cardOptions + '\'' +
//            ", installment='" + installment + '\'' +
            '}';
    }

    public static TossPayPrepareRequest of(PaymentPrepareRequest request,
        TossPayProperties properties) {
        return TossPayPrepareRequest.builder()
            .uniqueId(request.getUniqueId())
            .productName(request.getProductName())
            .price(request.getPrice())
            .callbackUrl(request.getCallbackUrl())
            .cancelUrl(request.getCancelUrl())
            .retAppScheme(null)
            .autoExecute(Boolean.FALSE)  // 자동결제 사용시 true
            .properties(properties)
            .build();
    }
}
