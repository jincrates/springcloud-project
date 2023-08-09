package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.PaymentGateway;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request.TossPayPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response.TossPayPrepareResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TossPay extends PaymentGateway {

    final String API_HOST = "https://pay.toss.im";

    @Override
    public PaymentPrepareResponse prepare(PaymentPrepareRequest request) {
        log.info("토스페이 결제준비");

        TossPayPrepareRequest prepareRequest = TossPayPrepareRequest.builder()
            .uniqueKey(request.getUniqueKey())
            .price(request.getPrice())
            .callbackUrl(request.getCallbackUrl())
            .cancelUrl(request.getCancelUrl())
            .retAppScheme(null)
            .build();

        TossPayPrepareResponse response = Optional.ofNullable(
            clientHelper.post(
                    API_HOST + "/api/v2/payments",
                    prepareRequest
                )
                .bodyToMono(new ParameterizedTypeReference<TossPayPrepareResponse>() {
                })
                .block()
        ).orElse(
            TossPayPrepareResponse.builder()
                .code(-1)
                .msg("통신실패")
                .build()
        );

        log.info("토스페이 결제준비 response: {}", response.toString());

        return response;
    }

    @Override
    public PaymentApproveResponse approve(PaymentApproveRequest request) {
        log.info("토스페이 결제승인 요청");
        return new PaymentApproveResponse();
    }

    private void getToken(PaymentPrepareRequest request) {
    }

}
