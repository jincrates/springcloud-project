package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentCancelResponse;

@Getter
@NoArgsConstructor
public class TossPayCancelResponse extends PaymentCancelResponse {

    private int code;                    // 응답코드(0: 성공, -1: 실패 - 실패사유는 msg, errorCode로 제공)
    private String msg;                  // 응답이 성공이 아닌 경우 설명 메시지
    private String errorCode;            // 에러코드
    private String mode;                 // 결제환경(LIVE: 실거래용, TEST: 테스트용)
    private String refundNo;             // 환불 번호
    private int refundableAmount;        // 환불 가능 금액: 환불 성공 후 남은 환불 가능 금액
    private int refundedAmount;          // 환불요청 금액
    private int paidAmount;              // 지불수단 승인금액: 총 금액 중 할인된 금액을 제외한 순수 지불수단 승인금액입니다.
    private String approvalTime;         // 결제건의 환불 처리 시간 (yyyy-MM-dd HH:mm:ss)
    private String orderNo;              // 승인된 상품 주문번호
    private String payToken;             // 환불된 결제토큰
    private String transactionId;        // 거래 트랜잭션 아이디

    @Builder
    private TossPayCancelResponse(int code, String msg, String errorCode, String mode,
        String refundNo,
        int refundableAmount, int refundedAmount, int paidAmount, String approvalTime,
        String orderNo,
        String payToken, String transactionId) {
        this.code = code;
        this.msg = msg;
        this.errorCode = errorCode;
        this.mode = mode;
        this.refundNo = refundNo;
        this.refundableAmount = refundableAmount;
        this.refundedAmount = refundedAmount;
        this.paidAmount = paidAmount;
        this.approvalTime = approvalTime;
        this.orderNo = orderNo;
        this.payToken = payToken;
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "TossPayCancelResponse{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            ", errorCode='" + errorCode + '\'' +
            ", mode='" + mode + '\'' +
            ", refundNo='" + refundNo + '\'' +
            ", refundableAmount=" + refundableAmount +
            ", refundedAmount=" + refundedAmount +
            ", paidAmount=" + paidAmount +
            ", approvalTime='" + approvalTime + '\'' +
            ", orderNo='" + orderNo + '\'' +
            ", payToken='" + payToken + '\'' +
            ", transactionId='" + transactionId + '\'' +
            '}';
    }

    public boolean isSuccess() {
        return code == 0 && msg == null;
    }
}
