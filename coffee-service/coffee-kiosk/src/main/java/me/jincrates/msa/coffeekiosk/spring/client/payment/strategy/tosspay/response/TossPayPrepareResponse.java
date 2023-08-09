package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;

@Getter
public class TossPayPrepareResponse extends PaymentPrepareResponse {

    private final int code;
    private final String msg;
    private final String payToken;
    private final String checkoutPage;

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
