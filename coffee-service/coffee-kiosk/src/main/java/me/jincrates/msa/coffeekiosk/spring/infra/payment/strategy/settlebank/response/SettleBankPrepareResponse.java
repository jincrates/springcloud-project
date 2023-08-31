package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.response;

import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.SettleBankProperties;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.SettleBankUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class SettleBankPrepareResponse extends PaymentPrepareResponse {

    private final String hdInfo;            // *전문정보 코드(고정) "IA_AUTHPAGE_1.0_1.0"
    private final String apiVer;            // *전문버전(고정) "1.0"
    private final String processType;       // *프로세스 구분(고정) "D"
    private final String mercntId;          // *헥토파이낸셜에서 부여하는 고유 상점아이디
    private final String ordNo;             // *가맹점에서 생성한 고유 주문번호(동일 거래일자에 유일성을 보장해야 함)
    private final String trDay;             // *가맹점에서 생성한 거래일자yyyyMMdd
    private final String trTime;            // *가맹점에서 생성한 거래일시HH24MISS
    private final String trPrice;           // *결제금액(AES 암호화)
    private final int trPricePlain;         // *결제금액
    private final String productNm;         // *상품명
    private final String callbackUrl;       // *결과통보 URL: 인증 완료후 결제창에서 호출될 URL
    private final String cancelUrl;         // *인증창취소 URL: 결제 과정중사용자 취소시 호출될 URL
    private final String signature;         // *sha256 방식으로 생성한 해쉬값

    private final String taxPrice;          // 과세금액(AES 암호화) - 결제금액 중 과세금액 면세여부가 복합과세일 경우만 해당
    private final String vatPrice;          // 부가세금액(AES 암호화) - 결제금액 중 부가세금액 과세금액의 10% 면세여부가 복합과세일 경우만 해당
    private final String dutyFreePrice;     // 면세금액(AES 암호화) - 결제금액 중 비과세금액 면세여부가 복합과세일 경우만 해당
    private final String containerDeposit;  // 자원순환보증금액(AES 암호화) - 자원순환보증금액 대상 거래인 경우 금액(현금영수증 비대상)
    private final String dutyFreeYn;        // 면세여부(Y:면세, N:과세, G:복합과세)
    private final String criPsblYn;         // 현금영수증발행가능여부(Y: 발행가능, N:발행불가능) - ""(빈값)일 경우 Y로 인식
    private final String addDeductionYn;    // 추가공제구분(대중교통: Y, 도서/공연비: C, 추가공제없음: N)
    private final String shopNm;            // 상점명
    private final String cphoneNo;          // 주문자 휴대폰번호(AES 암호화): "-" 제외
    private final String email;             // 주문자 이메일(AES 암호화)
    private final String custCi;            // 주문자 CI(AES 암호화) - 해당 파라메터의 값과 내통장결제측 고객 CI 값이 다를 경우 cancelUrl로 이동
    private final String regularpayYn;      // 자동결제등록여부: 추후 해당계좌로 자동결제를 이용할 것인지 여부
    private final String mercntParam1;      // 가맹점데이터1: 가맹점 측 추가 정보 1(해당 필드는 응답 및 거래 내역 조회시 리턴)
    private final String mercntParam2;      // 가맹점데이터2: 가맹점 측 추가 정보 1(해당 필드는 응답 및 거래 내역 조회시 리턴)
    private final String payLimitCd;        // 결제 한도 코드: 내통장결제에 설정되어 있는 정책에 따라 고객의 결제한도를 확인

    @Builder
    private SettleBankPrepareResponse(String uniqueId, int price, String productName,
                                      String callbackUrl, String cancelUrl, LocalDateTime preparedAt,
                                      String taxPrice, String vatPrice, String dutyFreePrice, String containerDeposit,
                                      String dutyFreeYn, String criPsblYn, String addDeductionYn, String shopName,
                                      String mobileNo, String email, String custCi, String regularpayYn, String customParam1,
                                      String customParam2, String payLimitCd, SettleBankProperties properties) {
        // Date
        final String currentDate = preparedAt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        final String currentTime = preparedAt.format(DateTimeFormatter.ofPattern("HHmmss"));

        // API Key
        final String apiKey = properties.getApiKey();
        final String secretKey = properties.getSecretKey();
        final String trPriceEnc = SettleBankUtils.aesEncryptEcb(properties.getSecretKey(), String.valueOf(price));
        final String hashValue = SettleBankUtils.sha256(
                apiKey + uniqueId + currentDate + currentTime + price + secretKey);

        // 필수
        this.hdInfo = "IA_AUTHPAGE_1.0_1.0";
        this.apiVer = "1.0";
        this.processType = "D";
        this.mercntId = apiKey;
        this.ordNo = uniqueId;
        this.trDay = currentDate;
        this.trTime = currentTime;
        this.trPrice = trPriceEnc;
        this.trPricePlain = price;
        this.productNm = productName;
        this.callbackUrl = callbackUrl;
        this.cancelUrl = cancelUrl;
        this.signature = hashValue;
        // 선택
        this.taxPrice = taxPrice;
        this.vatPrice = vatPrice;
        this.dutyFreePrice = dutyFreePrice;
        this.containerDeposit = containerDeposit;
        this.dutyFreeYn = dutyFreeYn;
        this.criPsblYn = criPsblYn;
        this.addDeductionYn = addDeductionYn;
        this.shopNm = shopName;
        this.cphoneNo = mobileNo;
        this.email = email;
        this.custCi = custCi;
        this.regularpayYn = regularpayYn;
        this.mercntParam1 = customParam1;
        this.mercntParam2 = customParam2;
        this.payLimitCd = payLimitCd;
    }

    @Override
    public String toString() {
        return "SettleBankPrepareResponse{" +
                "ordNo='" + ordNo + '\'' +
                ", trDay='" + trDay + '\'' +
                ", trTime='" + trTime + '\'' +
                ", trPrice='" + trPrice + '\'' +
                ", trPricePlain=" + trPricePlain +
                ", productNm='" + productNm + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", cancelUrl='" + cancelUrl + '\'' +
                ", signature='" + signature + '\'' +
                ", taxPrice='" + taxPrice + '\'' +
                ", vatPrice='" + vatPrice + '\'' +
                ", dutyFreePrice='" + dutyFreePrice + '\'' +
                ", containerDeposit='" + containerDeposit + '\'' +
                ", dutyFreeYn='" + dutyFreeYn + '\'' +
                ", criPsblYn='" + criPsblYn + '\'' +
                ", addDeductionYn='" + addDeductionYn + '\'' +
                ", shopNm='" + shopNm + '\'' +
                ", cphoneNo='" + cphoneNo + '\'' +
                ", email='" + email + '\'' +
                ", custCi='" + custCi + '\'' +
                ", regularpayYn='" + regularpayYn + '\'' +
                ", mercntParam1='" + mercntParam1 + '\'' +
                ", mercntParam2='" + mercntParam2 + '\'' +
                ", payLimitCd='" + payLimitCd + '\'' +
                '}';
    }

    public static SettleBankPrepareResponse of(PaymentPrepareRequest request,
                                               SettleBankProperties properties) {
        return SettleBankPrepareResponse.builder()
                .uniqueId(request.getUniqueId())
                .price(request.getPrice())
                .productName(request.getProductName())
                .callbackUrl(request.getCallbackUrl())
                .cancelUrl(request.getCancelUrl())
                .preparedAt(request.getPreparedAt())
                .properties(properties)
                .build();
    }
}
