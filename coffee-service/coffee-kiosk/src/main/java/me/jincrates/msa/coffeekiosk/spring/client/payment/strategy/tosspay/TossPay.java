package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.PaymentGateway;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request.TossPayApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.request.TossPayPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response.TossPayApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response.TossPayPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.WebClientHelper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPay implements PaymentGateway {

    private final WebClientHelper clientHelper;

    final String API_HOST = "https://pay.toss.im";

    @Override
    public PaymentPrepareResponse prepare(PaymentPrepareRequest request) {
        log.info("토스페이 결제준비 >>>");

        TossPayPrepareRequest prepareRequest = TossPayPrepareRequest.builder()
            .uniqueKey(request.getUniqueKey())
            .productName(request.getProductName())
            .price(request.getPrice())
            .callbackUrl(request.getCallbackUrl())
            .cancelUrl(request.getCancelUrl())
            .retAppScheme(null)
            .autoExecute(Boolean.FALSE)  // 자동결제 사용시 true
            .build();

        log.info("[Request] TossPay Prepare >>> {}", prepareRequest.toString());

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

        log.info("[Response] TossPay Prepare >>> {}", response.toString());

        if (!response.isSuccess()) {
            log.error("토스 결제준비 실패: {}", response.toString());
        }

        return response;
    }

    @Override
    public PaymentApproveResponse approve(PaymentApproveRequest request) {
        log.info("토스페이 결제승인 요청 >>>");

        TossPayApproveRequest approveRequest = TossPayApproveRequest.builder()
            .payToken(request.getAuthNo())
            .build();

        log.info("[Request] TossPay Approve >>> {}", approveRequest.toString());

        TossPayApproveResponse response = Optional.ofNullable(
            clientHelper.post(
                    API_HOST + "/api/v2/execute",
                    approveRequest
                )
                .bodyToMono(new ParameterizedTypeReference<TossPayApproveResponse>() {
                })
                .block()
        ).orElse(
            TossPayApproveResponse.builder()
                .code(-1)
                .msg("통신실패")
                .build()
        );

        log.info("[Response] TossPay Approve >>> {}", response.toString());

        if (!response.isSuccess()) {
            log.error("토스 결제승인 요청 실패: {}", response.toString());
        }

        return response;
    }
}
