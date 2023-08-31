package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentCancelRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.SettleBankProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.SettleBankUtils.aesEncryptEcb;
import static me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.SettleBankUtils.sha256;

@Getter
public class SettleBankCancelRequest {

    private final String hdInfo;         // *전문정보 코드(고정) - "IA_CANCEL"
    private final String apiVer;         // *전문버전(고정) - "3.0"
    private final String mercntId;       // *헥토파이낸셜에서 부여하는 고유 상점아이디
    private final String oldTrNo;        // *원거래번호: 헥토파이낸셜에서 결제요청에 대한 응답으로 전달한 거래번호
    private final String ordNo;          // *취소주문번호: 동일 거래일자에 유일성을 보장해야 함, 그래야 부분취소 가능
    private final String cancelPrice;    // *취소요청금액(AES 암호화): 거래금액 또는 부분취소 금액
    private final int cancelPricePlain;  // *취소요청금액: 거래금액 또는 부분취소 금액
    private final String reqDay;         // *가맹점에서 생성한 결제취소시 요청일자 yyyyMMdd
    private final String reqTime;        // *가맹점에서 생성한 결제취소시 요청시간 HH24MISS
    private final String signature;      // *sha256 방식으로 생성한 해쉬값

    @Builder
    private SettleBankCancelRequest(String uniqueId, int cancelPrice, String transactionId,
                                    LocalDateTime canceledAt, SettleBankProperties properties) {

        // Date
        final String currentDate = canceledAt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        final String currentTime = canceledAt.format(DateTimeFormatter.ofPattern("HHmmss"));

        // API Key
        final String apiKey = properties.getApiKey();
        final String secretKey = properties.getSecretKey();
        final String cancelPriceEnc = aesEncryptEcb(secretKey, String.valueOf(cancelPrice));
        final String hashValue = sha256(
                apiKey + transactionId + uniqueId + cancelPrice + currentDate + currentTime
                        + secretKey);

        // 필수
        this.hdInfo = "IA_CANCEL";
        this.apiVer = "3.0";
        this.mercntId = apiKey;
        this.oldTrNo = transactionId;
        this.ordNo = uniqueId + "_" + currentDate + currentTime;
        this.cancelPrice = cancelPriceEnc;
        this.cancelPricePlain = cancelPrice;
        this.reqDay = currentDate;
        this.reqTime = currentTime;
        this.signature = hashValue;
    }

    @Override
    public String toString() {
        return "SettleBankCancelRequest{" +
                "oldTrNo='" + oldTrNo + '\'' +
                ", ordNo='" + ordNo + '\'' +
                ", cancelPrice='" + cancelPrice + '\'' +
                ", cancelPricePlain=" + cancelPricePlain +
                ", reqDay='" + reqDay + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }

    public static SettleBankCancelRequest of(PaymentCancelRequest request,
                                             SettleBankProperties properties) {
        return SettleBankCancelRequest.builder()
                .uniqueId(request.getUniqueId())
                .cancelPrice(request.getCancelPrice())
                .transactionId(request.getTransactionId())
                .canceledAt(request.getCanceledAt())
                .properties(properties)
                .build();
    }
}
