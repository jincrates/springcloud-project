package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;

@Getter
@NoArgsConstructor
public class SettleBankApproveResponse extends PaymentApproveResponse {
    private int resultCd;
    private String resultMsg;
    private String errCd;
    private String ordNo;
    private String trNo;
    private String trPrice;
    private String trDay;
    private String trTime;

    @Builder
    private SettleBankApproveResponse(int resultCd, String resultMsg, String errCd, String ordNo, String trNo, String trPrice, String trDay, String trTime) {
        this.resultCd = resultCd;
        this.resultMsg = resultMsg;
        this.errCd = errCd;
        this.ordNo = ordNo;
        this.trNo = trNo;
        this.trPrice = trPrice;
        this.trDay = trDay;
        this.trTime = trTime;
    }
}
