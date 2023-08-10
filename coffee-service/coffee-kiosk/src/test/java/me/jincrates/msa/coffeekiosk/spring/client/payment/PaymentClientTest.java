package me.jincrates.msa.coffeekiosk.spring.client.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import me.jincrates.msa.coffeekiosk.spring.IntegrationTestSupport;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentApproveRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response.SettleBankApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response.SettleBankPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response.TossPayApproveResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.tosspay.response.TossPayPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PayMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PaymentClientTest extends IntegrationTestSupport {

    @Autowired
    PaymentClient paymentClient;

    @Test
    @DisplayName("내통장결제 준비")
    void prepareSettleBank() {
        // given
        LocalDateTime preparedAt = LocalDateTime.of(2023, 8, 9, 23, 30, 10);
        PaymentPrepareRequest request = PaymentPrepareRequest.builder()
            .uniqueKey("주문번호")
            .productName("상품명")
            .price(preparedAt.getSecond() * 10)
            .callbackUrl("http://jincrates.me/success")
            .cancelUrl("http://jincrates.me/cancel")
            .payMethod(PayMethod.SETTLE_BANK)
            .preparedAt(preparedAt)
            .build();

        // when
        PaymentPrepareResponse response = paymentClient.prepare(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(SettleBankPrepareResponse.class);

        SettleBankPrepareResponse result = (SettleBankPrepareResponse) response;
        assertThat(result).extracting("ordNo", "trDay", "trTime", "productNm", "trPrice",
                "callbackUrl", "cancelUrl")
            .contains("주문번호", "20230809", "233010", "상품명", "2bafa125406081ec765e5bc7ddeb7ddc",
                "http://jincrates.me/success", "http://jincrates.me/cancel");
    }

    @Test
    @DisplayName("토스페이 준비")
    void prepareTossPay() {
        // given
        LocalDateTime preparedAt = LocalDateTime.of(2023, 8, 9, 23, 30, 10);
        PaymentPrepareRequest request = PaymentPrepareRequest.builder()
            .uniqueKey("uniqueKey")
            .productName("상품명")
            .price(LocalDateTime.now().getSecond() * 10)
            .callbackUrl("callbackUrl")
            .cancelUrl("cancelUrl")
            .payMethod(PayMethod.TOSS_PAY)
            .preparedAt(preparedAt)
            .build();

        // when
        PaymentPrepareResponse response = paymentClient.prepare(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(TossPayPrepareResponse.class);

        TossPayPrepareResponse result = (TossPayPrepareResponse) response;
        assertThat(result.getCode()).isEqualTo(0);
        assertThat(result.getMsg()).isNull();
        assertThat(result.getPayToken()).isNotNull();
        assertThat(result.getCheckoutPage()).isNotNull();
    }

    @Test
    @DisplayName("내통장결제 결제요청")
    void approveSettleBank() {
        // given
        LocalDateTime approvedAt = LocalDateTime.of(2023, 8, 10, 0, 40, 30);
        PaymentApproveRequest request = PaymentApproveRequest.builder()
            .payMethod(PayMethod.SETTLE_BANK)
            .authNo("인증번호")
            .approvedAt(approvedAt)
            .build();

        // when
        PaymentApproveResponse response = paymentClient.approve(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(SettleBankApproveResponse.class);
    }

    @Test
    @DisplayName("토스페이 결제요청")
    void approveTossPay() {
        // given
        LocalDateTime approvedAt = LocalDateTime.of(2023, 8, 10, 0, 40, 30);
        PaymentApproveRequest request = PaymentApproveRequest.builder()
            .payMethod(PayMethod.TOSS_PAY)
            .authNo("인증번호")
            .approvedAt(approvedAt)
            .build();

        // when
        PaymentApproveResponse response = paymentClient.approve(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(TossPayApproveResponse.class);
    }
}