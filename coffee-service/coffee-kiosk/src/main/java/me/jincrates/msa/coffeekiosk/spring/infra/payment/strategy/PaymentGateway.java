package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy;

import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentCancelRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentStatusRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentStatusResponse;

public interface PaymentGateway {

    PaymentPrepareResponse prepare(PaymentPrepareRequest request);

    PaymentApproveResponse approve(PaymentApproveRequest request);

    PaymentStatusResponse status(PaymentStatusRequest request);

    PaymentCancelResponse cancel(PaymentCancelRequest request);
}
