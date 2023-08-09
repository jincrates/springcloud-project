package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;

@Getter
@NoArgsConstructor
public class TossPayPrepareResponse extends PaymentPrepareResponse {

    private int code;
    private String msg;
    private String payToken;
    private String checkoutPage;

    @Builder
    private TossPayPrepareResponse(int code, String msg, String payToken, String checkoutPage) {
        this.code = code;
        this.msg = msg;
        this.payToken = payToken;
        this.checkoutPage = checkoutPage;
    }

    @Override
    public String toString() {
        return "TossPayPrepareResponse{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            ", payToken='" + payToken + '\'' +
            ", checkoutPage='" + checkoutPage + '\'' +
            '}';
    }
}
