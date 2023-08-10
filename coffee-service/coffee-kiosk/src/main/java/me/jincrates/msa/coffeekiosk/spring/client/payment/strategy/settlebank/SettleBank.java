package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentCancelRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.PaymentGateway;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.request.SettleBankApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.request.SettleBankCancelRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response.SettleBankApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response.SettleBankCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response.SettleBankPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.WebClientHelper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SettleBank implements PaymentGateway {

    private final WebClientHelper clientHelper;
    private final SettleBankProperties properties;

    @Override
    public PaymentPrepareResponse prepare(PaymentPrepareRequest request) {

        SettleBankPrepareResponse response = SettleBankPrepareResponse.of(request, properties);

        log.info("SettleBank Prepare Response >>> {}", response.toString());

        return response;
    }

    @Override
    public PaymentApproveResponse approve(PaymentApproveRequest request) {

        SettleBankApproveRequest approveRequest = SettleBankApproveRequest.of(request, properties);

        log.info("[Request] SettleBank Approve >>> {}", approveRequest.toString());

        return clientHelper.post(properties.getApproveUri(),
                approveRequest)
            .bodyToMono(SettleBankApproveResponse.class)
            .doOnSuccess(response -> {
                log.info("[Response] SettleBank Approve >>> {}", response);

                if (!response.isSuccess()) {
                    log.error("내통장결제 결제승인 실패: {}", response);
                }
            })
            .onErrorResume(ex -> {
                log.error("내통장결제 통신 오류 발생 >>> {}", request);
                return Mono.just(SettleBankApproveResponse.builder()
                    .resultCd(-1)
                    .resultMsg(ex.getMessage())
                    .build());
            })
            .block();
    }

    @Override
    public PaymentCancelResponse cancel(PaymentCancelRequest request) {

        SettleBankCancelRequest cancelRequest = SettleBankCancelRequest.of(request, properties);

        log.info("[Request] SettleBank Cancel >>> {}", cancelRequest.toString());

        return clientHelper.post(properties.getCancelUri(),
                cancelRequest)
            .bodyToMono(SettleBankCancelResponse.class)
            .doOnSuccess(response -> {
                log.info("[Response] SettleBank Cancel >>> {}", response);

                if (!response.isSuccess()) {
                    log.error("내통장결제 결제취소 실패: {}", response);
                }
            })
            .onErrorResume(ex -> {
                log.error("내통장결제 통신 오류 발생 >>> {}", request);
                return Mono.just(SettleBankCancelResponse.builder()
                    .resultCd(-1)
                    .resultMsg(ex.getMessage())
                    .build());
            })
            .block();
    }
}
