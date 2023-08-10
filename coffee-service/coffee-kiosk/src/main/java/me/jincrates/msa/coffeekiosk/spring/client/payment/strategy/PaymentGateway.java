package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy;

import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentCancelRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentStatusRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentStatusResponse;

public interface PaymentGateway {

    PaymentPrepareResponse prepare(PaymentPrepareRequest request);

    PaymentApproveResponse approve(PaymentApproveRequest request);

    PaymentStatusResponse status(PaymentStatusRequest request);

    PaymentCancelResponse cancel(PaymentCancelRequest request);
}
