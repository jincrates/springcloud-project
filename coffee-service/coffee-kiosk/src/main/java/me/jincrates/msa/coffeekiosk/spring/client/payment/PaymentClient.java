package me.jincrates.msa.coffeekiosk.spring.client.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.PaymentGateway;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.SettleBank;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.TossPay;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PaymentMethod;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final SettleBank settleBank;
    private final TossPay tossPay;

    public PaymentPrepareResponse prepare(PaymentPrepareRequest request) {
        PaymentGateway paymentGateway = getPaymentClient(request.getPaymentMethod());
        return paymentGateway.prepare(request);
    }

    public PaymentApproveResponse approve(PaymentApproveRequest request) {
        PaymentGateway paymentGateway = getPaymentClient(request.getPaymentMethod());
        return paymentGateway.approve(request);
    }

    private PaymentGateway getPaymentClient(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case SETTLE_BANK -> settleBank;
            case TOSS_PAY -> tossPay;
        };
    }
}
