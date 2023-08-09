package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.PaymentGateway;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response.SettleBankPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.WebClientHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SettleBank implements PaymentGateway {

    private final WebClientHelper clientHelper;

    @Override
    public PaymentPrepareResponse prepare(PaymentPrepareRequest request) {
        log.info("내통장결제 결제준비");

        return SettleBankPrepareResponse.builder()
            .uniqueKey(request.getUniqueKey())
            .price(request.getPrice())
            .productName(request.getProductName())
            .callbackUrl(request.getCallbackUrl())
            .cancelUrl(request.getCancelUrl())
            .build();
    }

    @Override
    public PaymentApproveResponse approve(PaymentApproveRequest request) {
        log.info("내통장결제 결제승인 요청");
        return new PaymentApproveResponse();
    }
}
