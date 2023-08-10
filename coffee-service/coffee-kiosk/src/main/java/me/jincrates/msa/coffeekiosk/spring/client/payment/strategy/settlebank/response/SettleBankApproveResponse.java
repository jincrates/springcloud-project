package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;

@Getter
@NoArgsConstructor
public class SettleBankApproveResponse extends PaymentApproveResponse {

    private String apiVer;
    private int resultCd;
    private String errCd;
    private String resultMsg;
    private String ordNo;
    private String trNo;
    private String trPrice;
    private String trDay;
    private String trTime;

    @Builder
    private SettleBankApproveResponse(String apiVer, int resultCd, String errCd, String resultMsg,
        String ordNo,
        String trNo, String trPrice, String trDay, String trTime) {
        this.apiVer = apiVer;
        this.resultCd = resultCd;
        this.errCd = errCd;
        this.resultMsg = resultMsg;
        this.ordNo = ordNo;
        this.trNo = trNo;
        this.trPrice = trPrice;
        this.trDay = trDay;
        this.trTime = trTime;
    }

    @Override
    public String toString() {
        return "SettleBankApproveResponse{" +
            "apiVer='" + apiVer + '\'' +
            ", resultCd=" + resultCd +
            ", errCd='" + errCd + '\'' +
            ", resultMsg='" + resultMsg + '\'' +
            ", ordNo='" + ordNo + '\'' +
            ", trNo='" + trNo + '\'' +
            ", trPrice='" + trPrice + '\'' +
            ", trDay='" + trDay + '\'' +
            ", trTime='" + trTime + '\'' +
            '}';
    }
}
