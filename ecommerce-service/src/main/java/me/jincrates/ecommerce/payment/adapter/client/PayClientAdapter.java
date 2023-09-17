package me.jincrates.ecommerce.payment.adapter.client;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.payment.adapter.client.strategy.PayClient;
import me.jincrates.ecommerce.payment.application.port.PayClientPort;
import me.jincrates.ecommerce.payment.domain.PaymentType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
class PayClientAdapter implements PayClientPort {
    private final Map<PaymentType, PayClient> payClientMap;

    // TODO: paymentType에서 request로 변경
    public void prepare(PaymentType paymentType) {
        PayClient payClient = this.payClientMap.get(paymentType);
        payClient.prepare();
    }
}
