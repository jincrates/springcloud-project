package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response;

import static me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.SettleBankUtils.aesEncryptEcb;
import static me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.SettleBankUtils.sha256;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;

@Getter
public class SettleBankPrepareResponse extends PaymentPrepareResponse {

    private final String hdInfo;
    private final String apiVer;
    private final String processType;
    private final String mercntId;
    private final String ordNo;
    private final String trDay;
    private final String trTime;
    private final String trPrice;
    private final String productNm;
    private final String callbackUrl;
    private final String cancelUrl;
    private final String signature;

    @Builder
    private SettleBankPrepareResponse(String uniqueKey, int price, String productName,
        String callbackUrl, String cancelUrl) {

        // Data
        final LocalDateTime now = LocalDateTime.now();
        final String currentDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        final String currentTime = now.format(DateTimeFormatter.ofPattern("HHmmss"));

        // API Key
        final String apiKey = "M22B6529";
        final String secretKey = "SETTLEBANKISGOODSETTLEBANKISGOOD";
        final String trPriceEnc = aesEncryptEcb(secretKey, String.valueOf(price));
        final String hashValue = sha256(
            apiKey + uniqueKey + currentDate + currentTime + price + secretKey);

        this.hdInfo = "IA_AUTHPAGE_1.0_1.0";
        this.apiVer = "1.0";
        this.processType = "D";
        this.mercntId = apiKey;
        this.ordNo = uniqueKey;
        this.trDay = currentDate;
        this.trTime = currentTime;
        this.trPrice = trPriceEnc;
        this.productNm = productName;
        this.callbackUrl = callbackUrl;
        this.cancelUrl = cancelUrl;
        this.signature = hashValue;
    }
}
