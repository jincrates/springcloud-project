package me.jincrates.msa.coffeekiosk.spring.client.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import me.jincrates.msa.coffeekiosk.spring.IntegrationTestSupport;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PaymentMethod;
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
        PaymentPrepareRequest request = PaymentPrepareRequest.builder()
            .uniqueKey("주문번호")
            .productName("상품명")
            .price(LocalDateTime.now().getSecond() * 10)
            .callbackUrl("callbackUrl")
            .cancelUrl("cancelUrl")
            .paymentMethod(PaymentMethod.SETTLE_BANK)
            .build();

        // when
        PaymentPrepareResponse response = paymentClient.prepare(request);

        // then
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("토스페이 준비")
    void prepareTossPay() {
        // given
        PaymentPrepareRequest request = PaymentPrepareRequest.builder()
            .uniqueKey("uniqueKey")
            .productName("상품명")
            .price(LocalDateTime.now().getSecond() * 10)
            .callbackUrl("callbackUrl")
            .cancelUrl("cancelUrl")
            .paymentMethod(PaymentMethod.TOSS_PAY)
            .build();

        // when
        PaymentPrepareResponse prepare = paymentClient.prepare(request);

        // then
        assertThat(prepare).isNotNull();
    }
}