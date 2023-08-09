package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy;

import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.WebClientHelper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PaymentGateway {

    @Autowired
    protected WebClientHelper clientHelper;

    public abstract PaymentPrepareResponse prepare(PaymentPrepareRequest request);

    public abstract PaymentApproveResponse approve(PaymentApproveRequest request);
}
