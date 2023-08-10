package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank;

import java.util.Optional;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class SettleBank implements PaymentGateway {

    private final WebClientHelper clientHelper;
    private final SettleBankProperties properties;

    @Override
    public PaymentPrepareResponse prepare(PaymentPrepareRequest request) {
        log.info("내통장결제 결제준비 >>>");

        SettleBankPrepareResponse response = SettleBankPrepareResponse.of(request, properties);

        log.info("SettleBank Prepare Response >>> {}", response.toString());

        return response;
    }

    @Override
    public PaymentApproveResponse approve(PaymentApproveRequest request) {
        log.info("내통장결제 결제승인 요청 >>>");

        SettleBankApproveRequest approveRequest = SettleBankApproveRequest.of(request, properties);

        log.info("[Request] SettleBank Approve >>> {}", approveRequest.toString());

        SettleBankApproveResponse response = clientHelper.post(properties.getApproveUri(),
                approveRequest)
            .bodyToMono(SettleBankApproveResponse.class)
            .block();

        if (!response.isSuccess()) {
            log.error("내통장결제 결제승인 실패: {}", response.toString());
        }

        log.info("[Response] SettleBank Approve >>> {}", response.toString());

        return response;
    }

    @Override
    public PaymentCancelResponse cancel(PaymentCancelRequest request) {
        log.info("내통장결제 결제취소 요청 >>>");

        SettleBankCancelRequest cancelRequest = SettleBankCancelRequest.of(request, properties);

        log.info("[Request] SettleBank Cancel >>> {}", cancelRequest.toString());

        SettleBankCancelResponse response = Optional.ofNullable(
                clientHelper.post(properties.getCancelUri(),
                        cancelRequest)
                    .bodyToMono(SettleBankCancelResponse.class)
                    .block())
            .orElse(
                SettleBankCancelResponse.builder()
                    .resultCd(-1)
                    .resultMsg("통신실패")
                    .build());

        if (!response.isSuccess()) {
            log.error("내통장결제 결제취소 실패: {}", response.toString());
        }

        log.info("[Response] SettleBank Cancel >>> {}", response.toString());

        return response;
    }
}
