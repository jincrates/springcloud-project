package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentApproveResponse;

@Getter
@NoArgsConstructor
public class SettleBankApproveResponse extends PaymentApproveResponse {

    private String apiVer;              // *전문버전
    private int resultCd;               // *결과코드("0": 성공, "-1" 실패)
    private String errCd;               // *실패코드
    private String resultMsg;           // *결과메세지: 육안 식별 가능한 메세지이며, 서버측에 의해 임의 변동 가능성 존재
    private String mercntId;            // *헥토파이낸셜에서 부여하는 고유 상점아이디
    private String ordNo;               // *가맹점에서 요청시생성한 고유 주문번호
    private String trNo;                // *헥토파이낸셜에서 생성한 결제 거래번호
    private String trPrice;             // *가맹점에서 최초 결제 요청한 금액
    private String trDay;               // 결제 승인 일자 yyyyMMdd
    private String trTime;              // 결제 승인 일시 HH24MISS
    private String discntPrice;         // *할인금액: 결제 모듈에 의해 차감된 금액 (ex: 프로모션 선할인)
    private String payPrice;            // *결제금액: 최초 결제 요청 금액에서 할인된 최종 출금 금액
    private String criPrice;            // *현금영수증 발급 금액: 현금영수증 발급 금액 (현금성 결제 금액만 발급대상)
    private String criTaxVatPrice;      // *현금영수증 발급 금액 중 과세금액만 표기 (부가세 포함)
    private String criDutyFreePrice;    // *현금영수증 발급 금액 중 비과세금액만 표기
    private String regularpayKey;       // 자동결제키: 자동결제용으로 생성된 유니크한 키
    private String bankAcctNo;          // 계좌번호: 자동결제 키 발급 시 마스킹된 계좌번호 파라메터 응답
    private String bankCd;              // 은행코드: 자동결제 키 발급 시 은행코드 파라메터 응답


    @Builder
    private SettleBankApproveResponse(String apiVer, int resultCd, String errCd,
                                      String resultMsg, String mercntId, String ordNo, String trNo, String trPrice, String trDay,
                                      String trTime, String discntPrice, String payPrice, String criPrice, String criTaxVatPrice,
                                      String criDutyFreePrice, String regularpayKey, String bankAcctNo, String bankCd) {
        this.apiVer = apiVer;
        this.resultCd = resultCd;
        this.errCd = errCd;
        this.resultMsg = resultMsg;
        this.mercntId = mercntId;
        this.ordNo = ordNo;
        this.trNo = trNo;
        this.trPrice = trPrice;
        this.trDay = trDay;
        this.trTime = trTime;
        this.discntPrice = discntPrice;
        this.payPrice = payPrice;
        this.criPrice = criPrice;
        this.criTaxVatPrice = criTaxVatPrice;
        this.criDutyFreePrice = criDutyFreePrice;
        this.regularpayKey = regularpayKey;
        this.bankAcctNo = bankAcctNo;
        this.bankCd = bankCd;
    }

    @Override
    public String toString() {
        return "SettleBankApproveResponse{" +
                "resultCd=" + resultCd +
                ", errCd='" + errCd + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", ordNo='" + ordNo + '\'' +
                ", trNo='" + trNo + '\'' +
                ", trPrice='" + trPrice + '\'' +
                ", trDay='" + trDay + '\'' +
                ", trTime='" + trTime + '\'' +
                ", discntPrice='" + discntPrice + '\'' +
                ", payPrice='" + payPrice + '\'' +
                ", criPrice='" + criPrice + '\'' +
                ", criTaxVatPrice='" + criTaxVatPrice + '\'' +
                ", criDutyFreePrice='" + criDutyFreePrice + '\'' +
                ", regularpayKey='" + regularpayKey + '\'' +
                ", bankAcctNo='" + bankAcctNo + '\'' +
                ", bankCd='" + bankCd + '\'' +
                '}';
    }


    public boolean isSuccess() {
        return resultCd == 0;
    }
}
