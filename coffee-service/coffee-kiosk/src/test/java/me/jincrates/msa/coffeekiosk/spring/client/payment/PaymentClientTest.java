package me.jincrates.msa.coffeekiosk.spring.client.payment;

import me.jincrates.msa.coffeekiosk.spring.IntegrationTestSupport;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.PaymentClient;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentCancelRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.request.PaymentStatusRequest;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.response.PaymentStatusResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.response.SettleBankApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.response.SettleBankCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.response.SettleBankPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank.response.SettleBankStatusResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response.TossPayApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response.TossPayCancelResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response.TossPayPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.tosspay.response.TossPayStatusResponse;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.payment.PayMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentClientTest extends IntegrationTestSupport {

    @Autowired
    PaymentClient paymentClient;

    @TestFactory
    @DisplayName("결제준비 시나리오")
    Collection<DynamicTest> prepare() {
        // given
        LocalDateTime preparedAt = LocalDateTime.of(2023, 8, 12, 8, 40, 30);

        return List.of(
                DynamicTest.dynamicTest("내통장결제 결제수단을 통해 암호화된 결과를 응답받는다.", () -> {
                    // given
                    PaymentPrepareRequest request = createPrepareRequest(PayMethod.SETTLE_BANK,
                            preparedAt);

                    // when
                    PaymentPrepareResponse response = paymentClient.prepare(request);

                    // then
                    assertThat(response).isNotNull();
                    assertThat(response).isInstanceOf(SettleBankPrepareResponse.class);

                    SettleBankPrepareResponse result = (SettleBankPrepareResponse) response;
                    assertThat(result).extracting("ordNo", "productNm", "trDay", "trTime", "trPrice",
                                    "callbackUrl", "cancelUrl")
                            .contains("orderId", "상품명", "20230812", "084030",
                                    "b6365f245ea6d8d9c30abb07aad5db7f",
                                    "http://jincrates.me/success", "http://jincrates.me/cancel");
                }),
                DynamicTest.dynamicTest("토스 결제수단을 통해 결제인증에 필요한 토큰 데이터를 응답받는다.", () -> {
                    // given
                    PaymentPrepareRequest request = createPrepareRequest(PayMethod.TOSS_PAY,
                            preparedAt);

                    // when
                    PaymentPrepareResponse response = paymentClient.prepare(request);

                    // then
                    assertThat(response).isNotNull();
                    assertThat(response).isInstanceOf(TossPayPrepareResponse.class);

                    TossPayPrepareResponse result = (TossPayPrepareResponse) response;
                    assertThat(result.getCode()).isZero();
                    assertThat(result.getMsg()).isNull();
                    assertThat(result.getPayToken()).isNotNull();
                    assertThat(result.getCheckoutPage()).isNotNull();
                })
        );
    }

    @TestFactory
    @DisplayName("결제요청 시나리오")
    Collection<DynamicTest> approve() {
        // given
        LocalDateTime approvedAt = LocalDateTime.of(2023, 8, 12, 8, 40, 30);

        return List.of(
                DynamicTest.dynamicTest("내통장결제 결제인증 토큰을 통해 결제승인 요청에 대한 결과를 응답받는다.", () -> {
                    // given
                    PaymentApproveRequest request = createApproveRequest(PayMethod.SETTLE_BANK,
                            approvedAt);

                    // when
                    PaymentApproveResponse response = paymentClient.approve(request);

                    // then
                    assertThat(response).isNotNull();
                    assertThat(response).isInstanceOf(SettleBankApproveResponse.class);
                }),
                DynamicTest.dynamicTest("토스 결제인증 토큰을 통해 결제승인 요청에 대한 결과를 응답받는다.", () -> {
                    // given
                    PaymentApproveRequest request = createApproveRequest(PayMethod.TOSS_PAY,
                            approvedAt);

                    // when
                    PaymentApproveResponse response = paymentClient.approve(request);

                    // then
                    assertThat(response).isNotNull();
                    assertThat(response).isInstanceOf(TossPayApproveResponse.class);
                })
        );
    }

    @TestFactory
    @DisplayName("결제상태 조회 시나리오")
    Collection<DynamicTest> status() {
        // given
        LocalDateTime searchedAt = LocalDateTime.of(2023, 8, 12, 8, 40, 30);

        return List.of(
                DynamicTest.dynamicTest("내통장결제 결제인증 토큰을 통해 결제상태 조회에 대한 결과를 응답받는다.", () -> {
                    // given
                    PaymentStatusRequest request = createStatusRequest(PayMethod.SETTLE_BANK,
                            searchedAt);

                    // when
                    PaymentStatusResponse response = paymentClient.status(request);

                    // then
                    assertThat(response).isNotNull();
                    assertThat(response).isInstanceOf(SettleBankStatusResponse.class);
                }),
                DynamicTest.dynamicTest("토스 결제인증 토큰을 통해 결제상태 조회에 대한 결과를 응답받는다.", () -> {
                    // given
                    PaymentStatusRequest request = createStatusRequest(PayMethod.TOSS_PAY,
                            searchedAt);

                    // when
                    PaymentStatusResponse response = paymentClient.status(request);

                    // then
                    assertThat(response).isNotNull();
                    assertThat(response).isInstanceOf(TossPayStatusResponse.class);
                })
        );
    }

    @TestFactory
    @DisplayName("결제취소 시나리오")
    Collection<DynamicTest> cancel() {
        // given
        LocalDateTime canceledAt = LocalDateTime.of(2023, 8, 12, 8, 40, 30);

        return List.of(
                DynamicTest.dynamicTest("내통장결제 트랜잭션 ID를 통해 결제취소에 대한 결과를 응답받는다.", () -> {
                    // given
                    PaymentCancelRequest request = createCancelRequest(PayMethod.SETTLE_BANK,
                            canceledAt);

                    // when
                    PaymentCancelResponse response = paymentClient.cancel(request);

                    // then
                    assertThat(response).isNotNull();
                    assertThat(response).isInstanceOf(SettleBankCancelResponse.class);
                }),
                DynamicTest.dynamicTest("토스 결제인증 토큰을 통해 결제취소에 대한 결과를 응답받는다.", () -> {
                    // given
                    PaymentCancelRequest request = createCancelRequest(PayMethod.TOSS_PAY,
                            canceledAt);

                    // when
                    PaymentCancelResponse response = paymentClient.cancel(request);

                    // then
                    assertThat(response).isNotNull();
                    assertThat(response).isInstanceOf(TossPayCancelResponse.class);
                })
        );
    }

    private PaymentPrepareRequest createPrepareRequest(PayMethod payMethod,
                                                       LocalDateTime preparedAt) {
        return PaymentPrepareRequest.builder()
                .payMethod(payMethod)
                .uniqueId("orderId")
                .productName("상품명")
                .price(preparedAt.getSecond() * 10)
                .callbackUrl("http://jincrates.me/success")
                .cancelUrl("http://jincrates.me/cancel")
                .preparedAt(preparedAt)
                .build();
    }

    private PaymentApproveRequest createApproveRequest(PayMethod payMethod,
                                                       LocalDateTime approvedAt) {
        return PaymentApproveRequest.builder()
                .payMethod(payMethod)
                .authNo("인증번호")
                .approvedAt(approvedAt)
                .build();
    }

    private PaymentStatusRequest createStatusRequest(PayMethod payMethod,
                                                     LocalDateTime searchedAt) {
        return PaymentStatusRequest.builder()
                .payMethod(payMethod)
                .authNo("인증번호")
                .uniqueId("orderId")
                .searchedAt(searchedAt)
                .build();
    }

    private PaymentCancelRequest createCancelRequest(PayMethod payMethod,
                                                     LocalDateTime canceledAt) {
        return PaymentCancelRequest.builder()
                .payMethod(payMethod)
                .uniqueId("orderId")
                .transactionId("트랜잭션 ID")
                .cancelPrice(canceledAt.getSecond() * 100)
                .canceledAt(canceledAt)
                .build();
    }
}