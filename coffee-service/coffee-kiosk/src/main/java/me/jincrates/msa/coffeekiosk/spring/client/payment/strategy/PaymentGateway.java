package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy;

import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;

public interface PaymentGateway {

    PaymentPrepareResponse prepare(PaymentPrepareRequest request);

    PaymentApproveResponse approve(PaymentApproveRequest request);
}
