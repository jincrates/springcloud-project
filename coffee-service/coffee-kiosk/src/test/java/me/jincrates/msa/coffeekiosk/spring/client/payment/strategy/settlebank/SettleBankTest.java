package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import me.jincrates.msa.coffeekiosk.spring.IntegrationTestSupport;
import me.jincrates.msa.coffeekiosk.spring.client.payment.request.PaymentPrepareRequest;
import me.jincrates.msa.coffeekiosk.spring.client.payment.response.PaymentPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank.response.SettleBankPrepareResponse;
import me.jincrates.msa.coffeekiosk.spring.domain.payment.PayMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SettleBankTest extends IntegrationTestSupport {

    @Autowired
    SettleBank settleBank;

    @Test
    @DisplayName("내통장결제 결제인증을 위한 암호화 데이터를 리턴합니다.")
    void prepare() {
        // given
        LocalDateTime preparedAt = LocalDateTime.of(2023, 8, 9, 23, 30, 10);
        PaymentPrepareRequest request = PaymentPrepareRequest.builder()
            .uniqueId("주문번호")
            .productName("상품명")
            .price(preparedAt.getSecond() * 10)
            .callbackUrl("http://jincrates.me/success")
            .cancelUrl("http://jincrates.me/cancel")
            .payMethod(PayMethod.SETTLE_BANK)
            .preparedAt(preparedAt)
            .build();

        // when
        PaymentPrepareResponse response = settleBank.prepare(request);

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
    void approve() {
    }
}