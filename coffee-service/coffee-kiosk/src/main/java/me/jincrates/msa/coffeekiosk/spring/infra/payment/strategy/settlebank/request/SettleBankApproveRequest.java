package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.request;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.SettleBankProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.SettleBankUtils.sha256;

@Getter
public class SettleBankApproveRequest {

    private final String hdInfo;    // *전문정보 코드(고정) - "IA_APPROV"
    private final String apiVer;    // *전문버전(고정) - "3.0"
    private final String mercntId;  // *헥토파이낸셜에서 부여하는 고유 상점아이디
    private final String authNo;    // *인증번호: 헥토파이낸셜에서 생성한 인증 단계 응답값
    private final String reqDay;    // *가맹점에서 생성한 결제승인시 요청일자 yyyyMMdd
    private final String reqTime;   // *가맹점에서 생성한 결제승인시 요청시간 HH24MISS
    private final String signature;   // *sha256 방식으로 생성한 해쉬값

    private final String custParam1;  // 가맹점 추가필드1: 승인요청시 정의한 요청필드 외 가맹점에서 임의로 정의 저장되는 필드값(거래조회시 해당 값이 응답전문에 표기)
    private final String custParam2;  // 가맹점 추가필드2: 승인요청시 정의한 요청필드 외 가맹점에서 임의로 정의 저장되는 필드값(거래조회시 해당 값이 응답전문에 표기)
    private final String custParam3;  // 가맹점 추가필드3: 승인요청시 정의한 요청필드 외 가맹점에서 임의로 정의 저장되는 필드값(거래조회시 해당 값이 응답전문에 표기)
    private final String custParam4;  // 가맹점 추가필드4: 승인요청시 정의한 요청필드 외 가맹점에서 임의로 정의 저장되는 필드값(거래조회시 해당 값이 응답전문에 표기)


    @Builder
    private SettleBankApproveRequest(String authNo, LocalDateTime approvedAt, String customParam1,
                                     String customParam2, String customParam3, String customParam4,
                                     SettleBankProperties properties) {

        // Date
        final String currentDate = approvedAt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        final String currentTime = approvedAt.format(DateTimeFormatter.ofPattern("HHmmss"));

        // API Key
        final String apiKey = properties.getApiKey();
        final String secretKey = properties.getSecretKey();
        final String hashValue = sha256(apiKey + authNo + currentDate + currentTime + secretKey);

        // 필수
        this.hdInfo = "IA_APPROV";
        this.apiVer = "3.0";
        this.mercntId = apiKey;
        this.authNo = authNo;
        this.reqDay = currentDate;
        this.reqTime = currentTime;
        this.signature = hashValue;
        // 선택
        this.custParam1 = customParam1;
        this.custParam2 = customParam2;
        this.custParam3 = customParam3;
        this.custParam4 = customParam4;
    }

    @Override
    public String toString() {
        return "SettleBankApproveRequest{" +
                "authNo='" + authNo + '\'' +
                ", reqDay='" + reqDay + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", signature='" + signature + '\'' +
                ", custParam1='" + custParam1 + '\'' +
                ", custParam2='" + custParam2 + '\'' +
                ", custParam3='" + custParam3 + '\'' +
                ", custParam4='" + custParam4 + '\'' +
                '}';
    }

    public static SettleBankApproveRequest of(PaymentApproveRequest request,
                                              SettleBankProperties properties) {
        return SettleBankApproveRequest.builder()
                .authNo(request.getAuthNo())
                .approvedAt(request.getApprovedAt())
                .properties(properties)
                .build();
    }
}
