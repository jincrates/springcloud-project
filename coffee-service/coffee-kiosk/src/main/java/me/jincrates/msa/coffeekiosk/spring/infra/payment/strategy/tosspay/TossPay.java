package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeekiosk.spring.global.common.WebClientHelper;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentCancelRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentStatusRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentStatusResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.PaymentGateway;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.request.TossPayApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.request.TossPayCancelRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.request.TossPayPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.request.TossPayStatusRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response.TossPayApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response.TossPayCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response.TossPayPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response.TossPayStatusResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPay implements PaymentGateway {

    private final WebClientHelper clientHelper;
    private final TossPayProperties properties;

    final String API_HOST = "https://pay.toss.im";
    final String PREPARE_URL = "/api/v2/payments";
    final String APPROVE_URL = "/api/v2/payments";

    @Override
    public PaymentPrepareResponse prepare(PaymentPrepareRequest request) {

        TossPayPrepareRequest prepareRequest = TossPayPrepareRequest.of(request, properties);

        log.info("[Request] TossPay Prepare >>> {}", prepareRequest.toString());

        return clientHelper.post(properties.getPrepareUri(),
                        prepareRequest
                )
                .bodyToMono(TossPayPrepareResponse.class)
                .doOnSuccess(response -> {
                    log.info("[Response] TossPay Prepare >>> {}", response);
                    if (!response.isSuccess()) {
                        log.error("토스페이 결제준비 실패: {}", response);
                    }
                })
                .onErrorResume(ex -> {
                    log.error("토스페이 통신 오류 발생 >>> {}", request);
                    return Mono.just(TossPayPrepareResponse.builder()
                            .code(-1)
                            .msg(ex.getMessage())
                            .build());
                })
                .block();
    }

    @Override
    public PaymentApproveResponse approve(PaymentApproveRequest request) {

        TossPayApproveRequest approveRequest = TossPayApproveRequest.of(request, properties);

        log.info("[Request] TossPay Approve >>> {}", approveRequest.toString());

        return clientHelper.post(properties.getApproveUri(),
                        approveRequest
                )
                .bodyToMono(TossPayApproveResponse.class)
                .doOnSuccess(response -> {
                    log.info("[Response] TossPay Approve >>> {}", response);
                    if (!response.isSuccess()) {
                        log.error("토스페이 결제승인 실패: {}", response);
                    }
                })
                .onErrorResume(ex -> {
                    log.error("토스페이 통신 오류 발생 >>> {}", request);
                    return Mono.just(TossPayApproveResponse.builder()
                            .code(-1)
                            .msg(ex.getMessage())
                            .build());
                })
                .block();
    }

    @Override
    public PaymentStatusResponse status(PaymentStatusRequest request) {

        TossPayStatusRequest statusRequest = TossPayStatusRequest.of(request, properties);

        log.info("[Request] TossPay Status >>> {}", statusRequest.toString());

        return clientHelper.post(properties.getStatusUri(),
                        statusRequest
                )
                .bodyToMono(TossPayStatusResponse.class)
                .doOnSuccess(response -> {
                    log.info("[Response] TossPay Status >>> {}", response);
                    if (!response.isSuccess()) {
                        log.error("토스페이 결제상태 조회 실패: {}", response);
                    }
                })
                .onErrorResume(ex -> {
                    log.error("토스페이 통신 오류 발생 >>> {}", request);
                    return Mono.just(TossPayStatusResponse.builder()
                            .code(-1)
                            .msg(ex.getMessage())
                            .build());
                })
                .block();
    }

    @Override
    public PaymentCancelResponse cancel(PaymentCancelRequest request) {

        TossPayCancelRequest cancelRequest = TossPayCancelRequest.builder()
                .payToken("인증")
                .reason(request.getReason())
                .properties(properties)
                .build();

        log.info("[Request] TossPay Cancel >>> {}", cancelRequest.toString());

        return clientHelper.post(properties.getCancelUri(),
                        cancelRequest
                )
                .bodyToMono(TossPayCancelResponse.class)
                .doOnSuccess(response -> {
                    log.info("[Response] TossPay Cancel >>> {}", response);
                    if (!response.isSuccess()) {
                        log.error("토스페이 결제취소 실패: {}", response);
                    }
                })
                .onErrorResume(ex -> {
                    log.error("토스페이 통신 오류 발생 >>> {}", request);
                    return Mono.just(TossPayCancelResponse.builder()
                            .code(-1)
                            .msg(ex.getMessage())
                            .build());
                })
                .block();
    }
}
