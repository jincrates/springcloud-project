package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentPrepareResponse;

@Getter
@NoArgsConstructor
public class TossPayPrepareResponse extends PaymentPrepareResponse {

    private int code;             // 응답코드(0: 성공, -1: 실패)
    private String payToken;      // 토스페이 토큰: 매회 유니크한 토큰 값이 생성됩니다. 가맹점에서는 이 값을 반드시 저장하고 관리하셔야 합니다.
    private String checkoutPage;  // 결제를 진행할 수 있는 토스페이 웹페이지 URL
    private String msg;           // 응답이 성공이 아닌 경우 설명 메세지
    private String errorCode;     // 에러 코드


    @Builder
    private TossPayPrepareResponse(int code, String payToken, String checkoutPage, String msg,
                                   String errorCode) {
        this.code = code;
        this.payToken = payToken;
        this.checkoutPage = checkoutPage;
        this.msg = msg;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "TossPayPrepareResponse{" +
                "code=" + code +
                ", payToken='" + payToken + '\'' +
                ", checkoutPage='" + checkoutPage + '\'' +
                ", msg='" + msg + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return code == 0 && msg == null;
    }
}
