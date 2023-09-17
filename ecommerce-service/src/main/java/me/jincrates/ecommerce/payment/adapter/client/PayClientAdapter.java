package me.jincrates.ecommerce.payment.adapter.client;

import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.payment.application.port.PayClientPort;
import me.jincrates.ecommerce.payment.domain.PaymentType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PayClientAdapter implements PayClientPort {
    //private final PayClient payClient;

    // paymentType에서 request로 변경
    public void pay(PaymentType paymentType) {
        //PayClient payClient = this.payClient.get(paymentType);
        //payClient.get();
    }

}
