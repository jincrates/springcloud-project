package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentApproveResponse;

@Getter
@NoArgsConstructor
public class TossPayApproveResponse extends PaymentApproveResponse {

    private int code;                    // 응답코드(0: 성공, -1: 실패 - 실패사유는 msg, errorCode로 제공)
    private String msg;                  // 응답이 성공이 아닌 경우 설명 메시지
    private String errorCode;            // 에러코드
    private String mode;                 // 결제환경(LIVE: 실거래용, TEST: 테스트용)
    private String approvalTime;         // 결제건의 승인처리 시간(yyyy-MM-dd HH:mm:ss)
    private String stateMsg;             // 상태 응답 텍스트 값
    private int amount;                  // 상품금액: 결제 생성 시 요청 된 총 금액
    private int discountedAmount;        // 할인된 금액
    private int paidAmount;              // 지불수단 승인금액: 총 금액 중 할인된 금액을 제외한 순수 지불수단 승인금액입니다.
    private String payMethod;            // 결제수단(TOSS_MONEY: 토스머니, CARD: 카드)
    private String orderNo;              // 승인된 상품 주문번호
    private String payToken;             // 승인된 결제토큰
    private String transactionId;        // 거래 트랜잭션 아이디
    private String cashReceiptMgtKey;    // 현금영수증 관리번호 식별값: 국세청 승인번호는 아니며 토스페이에서 자체적으로 만든 식별 값입니다.
    private String cardCompanyCode;      // 카드사 코드
    private String cardCompanyName;      // 승인 카드사명
    private String cardAuthorizationNo;  // 구매자가 확인할 수 있는 카드사 승인번호
    private String spreadOut;            // 사용자가 선택한 카드 할부개월: 5만원 미만 금액 및 일시불 결제의 경우 0으로 리턴됩니다.
    private boolean noInterest;          // 카드 무이자 적용 여부(true: 무이자, false: 일반)
    private String salesCheckLinkUrl;    // 신용카드 매출전표 호출URL
    private String cardMethodType;       // 카드 타입(CREDIT: 신용카드, CHECK: 체크카드, PREPAYMENT: 선불카드)
    private String cardNumber;           // 마스킹된 카드번호
    private String accountBankCode;      // 은행 코드
    private String accountBankName;      // 은행 명

    @Builder
    private TossPayApproveResponse(int code, String msg, String errorCode, String mode,
                                   String approvalTime, String stateMsg, int amount, int discountedAmount, int paidAmount,
                                   String payMethod, String orderNo, String payToken, String transactionId,
                                   String cashReceiptMgtKey, String cardCompanyCode, String cardCompanyName,
                                   String cardAuthorizationNo, String spreadOut, boolean noInterest, String salesCheckLinkUrl,
                                   String cardMethodType, String cardNumber, String accountBankCode, String accountBankName) {
        this.code = code;
        this.msg = msg;
        this.errorCode = errorCode;
        this.mode = mode;
        this.approvalTime = approvalTime;
        this.stateMsg = stateMsg;
        this.amount = amount;
        this.discountedAmount = discountedAmount;
        this.paidAmount = paidAmount;
        this.payMethod = payMethod;
        this.orderNo = orderNo;
        this.payToken = payToken;
        this.transactionId = transactionId;
        this.cashReceiptMgtKey = cashReceiptMgtKey;
        this.cardCompanyCode = cardCompanyCode;
        this.cardCompanyName = cardCompanyName;
        this.cardAuthorizationNo = cardAuthorizationNo;
        this.spreadOut = spreadOut;
        this.noInterest = noInterest;
        this.salesCheckLinkUrl = salesCheckLinkUrl;
        this.cardMethodType = cardMethodType;
        this.cardNumber = cardNumber;
        this.accountBankCode = accountBankCode;
        this.accountBankName = accountBankName;
    }

    @Override
    public String toString() {
        return "TossPayApproveResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", mode='" + mode + '\'' +
                ", approvalTime='" + approvalTime + '\'' +
                ", stateMsg='" + stateMsg + '\'' +
                ", amount=" + amount +
                ", discountedAmount=" + discountedAmount +
                ", paidAmount=" + paidAmount +
                ", payMethod='" + payMethod + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", payToken='" + payToken + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", cashReceiptMgtKey='" + cashReceiptMgtKey + '\'' +
                ", cardCompanyCode='" + cardCompanyCode + '\'' +
                ", cardCompanyName='" + cardCompanyName + '\'' +
                ", cardAuthorizationNo='" + cardAuthorizationNo + '\'' +
                ", spreadOut='" + spreadOut + '\'' +
                ", noInterest=" + noInterest +
                ", salesCheckLinkUrl='" + salesCheckLinkUrl + '\'' +
                ", cardMethodType='" + cardMethodType + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", accountBankCode='" + accountBankCode + '\'' +
                ", accountBankName='" + accountBankName + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return code == 0 && msg == null;
    }
}
