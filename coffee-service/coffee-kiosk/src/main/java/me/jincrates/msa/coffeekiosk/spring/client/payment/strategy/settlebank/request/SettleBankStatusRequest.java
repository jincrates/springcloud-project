package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.request;

import static me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.SettleBankUtils.sha256;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentStatusRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.SettleBankProperties;

@Getter
public class SettleBankStatusRequest {

    private final String hdInfo;         // *전문정보 코드(고정) - "IA_MO_1.0_1.0"
    private final String apiVer;         // *전문버전(고정) - "1.0"
    private final String mercntId;       // *헥토파이낸셜에서 부여하는 고유 상점아이디
    private final String ordNo;          // *주문번호: 가맹점에서 생성한 결제 주문번호
    private final String trDay;          // *거래일자: 가맹점에서 주문시 생성한 거래일자 yyyyMMdd
    private final String reqDay;         // *가맹점에서 생성한 결제취소시 요청일자 yyyyMMdd
    private final String reqTime;        // *가맹점에서 생성한 결제취소시 요청시간 HH24MISS
    private final String signature;      // *sha256 방식으로 생성한 해쉬값

    @Builder
    private SettleBankStatusRequest(String uniqueId, LocalDateTime searchedAt,
        SettleBankProperties properties) {

        // Date
        final String currentDate = searchedAt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        final String currentTime = searchedAt.format(DateTimeFormatter.ofPattern("HHmmss"));

        // API Key
        final String apiKey = properties.getApiKey();
        final String secretKey = properties.getSecretKey();
        final String hashValue = sha256(
            apiKey + uniqueId + currentDate + currentDate + currentTime + secretKey);

        // 필수
        this.hdInfo = "IA_MO_1.0_1.0";
        this.apiVer = "1.0";
        this.mercntId = apiKey;
        this.ordNo = uniqueId;
        this.trDay = currentDate;
        this.reqDay = currentDate;
        this.reqTime = currentTime;
        this.signature = hashValue;
    }

    @Override
    public String toString() {
        return "SettleBankStatusRequest{" +
            "hdInfo='" + hdInfo + '\'' +
            ", apiVer='" + apiVer + '\'' +
            ", mercntId='" + mercntId + '\'' +
            ", ordNo='" + ordNo + '\'' +
            ", trDay='" + trDay + '\'' +
            ", reqDay='" + reqDay + '\'' +
            ", reqTime='" + reqTime + '\'' +
            ", signature='" + signature + '\'' +
            '}';
    }

    public static SettleBankStatusRequest of(PaymentStatusRequest request,
        SettleBankProperties properties) {
        return SettleBankStatusRequest.builder()
            .uniqueId(request.getUniqueId())
            .searchedAt(request.getSearchedAt())
            .properties(properties)
            .build();
    }
}
