package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TossPayProperties {

    private final String apiKey = "sk_test_mgkXl3jBk5mgkX1AnB25";
    private final String apiHost = "https://pay.toss.im";
    private final String preparePath = "/api/v2/payments";
    private final String approvePath = "/api/v2/execute";
    private final String statusPath = "/api/v2/status";
    private final String cancelPath = "/api/v2/refunds";


    public String getApiKey() {
        return apiKey;
    }


    public String getPrepareUri() {
        return apiHost + preparePath;
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
