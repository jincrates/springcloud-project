package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SettleBankProperties {

    private final String apiKey = "M22B6529";
    private final String secretKey = "SETTLEBANKISGOODSETTLEBANKISGOOD";
    private final String apiHost = "https://tbEzauthapi.settlebank.co.kr";
    private final String approvePath = "/v3/APIPayApprov.do";
    private final String statusPath = "/APIMoInfo.do";
    private final String cancelPath = "/v3/APIPayCancel.do";


    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getApproveUri() {
        return apiHost + approvePath;
    }

    public String getStatusUri() {
        return apiHost + statusPath;
    }

    public String getCancelUri() {
        return apiHost + cancelPath;
    }
}
