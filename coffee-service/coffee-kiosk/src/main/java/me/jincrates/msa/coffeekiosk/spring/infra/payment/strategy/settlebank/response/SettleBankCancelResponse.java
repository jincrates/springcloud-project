package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentCancelResponse;

@Getter
@NoArgsConstructor
public class SettleBankCancelResponse extends PaymentCancelResponse {

    private String apiVer;              // *전문버전
    private int resultCd;               // *결과코드("0": 성공, "-1" 실패)
    private String errCd;               // *실패코드
    private String resultMsg;           // *결과메세지: 육안 식별 가능한 메세지이며, 서버측에 의해 임의 변동 가능성 존재
    private String oldTrNo;             // *취소원거래번호
    private String trNo;                // *헥토파이낸셜에서 취소시 생성된 거래번호
    private String cancelPrice;         // *취소금액
    private String disntCancelPrice;    // *취소 할인(프로모션) 금액
    private String payCancelPrice;      // *취소 거래 금액 중 할인을 제외한 취소 금액
    private String criPrice;            // *현금영수증 발급 금액: 현금영수증 발급 금액 (현금성 결제 금액만 발급대상)
    private String criTaxVatPrice;      // *현금영수증 발급 금액 중 과세금액만 표기 (부가세 포함)
    private String criDutyFreePrice;    // *현금영수증 발급 금액 중 비과세금액만 표기
    private String cancelDay;           // 취소일자 yyyyMMdd

    @Builder
    private SettleBankCancelResponse(String apiVer, int resultCd, String errCd, String resultMsg,
                                     String oldTrNo, String trNo, String cancelPrice, String disntCancelPrice,
                                     String payCancelPrice,
                                     String criPrice, String criTaxVatPrice, String criDutyFreePrice, String cancelDay) {
        this.apiVer = apiVer;
        this.resultCd = resultCd;
        this.errCd = errCd;
        this.resultMsg = resultMsg;
        this.oldTrNo = oldTrNo;
        this.trNo = trNo;
        this.cancelPrice = cancelPrice;
        this.disntCancelPrice = disntCancelPrice;
        this.payCancelPrice = payCancelPrice;
        this.criPrice = criPrice;
        this.criTaxVatPrice = criTaxVatPrice;
        this.criDutyFreePrice = criDutyFreePrice;
        this.cancelDay = cancelDay;
    }

    @Override
    public String toString() {
        return "SettleBankCancelResponse{" +
                "resultCd=" + resultCd +
                ", errCd='" + errCd + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", oldTrNo='" + oldTrNo + '\'' +
                ", trNo='" + trNo + '\'' +
                ", cancelPrice='" + cancelPrice + '\'' +
                ", disntCancelPrice='" + disntCancelPrice + '\'' +
                ", payCancelPrice='" + payCancelPrice + '\'' +
                ", criPrice='" + criPrice + '\'' +
                ", criTaxVatPrice='" + criTaxVatPrice + '\'' +
                ", criDutyFreePrice='" + criDutyFreePrice + '\'' +
                ", cancelDay='" + cancelDay + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return resultCd == 0;
    }
}
