package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.SettleBankUtils.sha256;

@Getter
public class SettleBankApproveRequest {
    private final String hdInfo;
    private final String apiVer;
    private final String mercntId;
    private final String reqDay;
    private final String reqTime;
    private final String signature;
    private final String authNo;


    @Builder
    private SettleBankApproveRequest(String authNo, LocalDateTime approvedAt) {

        // Date
        final String currentDate = approvedAt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        final String currentTime = approvedAt.format(DateTimeFormatter.ofPattern("HHmmss"));

        // API Key
        final String apiKey = "M22B6529";
        final String secretKey = "SETTLEBANKISGOODSETTLEBANKISGOOD";
        final String hashValue = sha256(apiKey + authNo + currentDate + currentTime + secretKey);

        this.hdInfo = "IA_APPROV";
        this.apiVer = "3.0";
        this.mercntId = apiKey;
        this.reqDay = currentDate;
        this.reqTime = currentTime;
        this.signature = hashValue;
        this.authNo = authNo;
    }
}
