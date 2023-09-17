package me.jincrates.ecommerce.payment.config;

import me.jincrates.ecommerce.payment.adapter.client.strategy.PayClient;
import me.jincrates.ecommerce.payment.adapter.client.strategy.kakaopay.KakaoPay;
import me.jincrates.ecommerce.payment.adapter.client.strategy.naverpay.NaverPay;
import me.jincrates.ecommerce.payment.adapter.client.strategy.portone.PortOne;
import me.jincrates.ecommerce.payment.adapter.client.strategy.settlepay.SettlePay;
import me.jincrates.ecommerce.payment.adapter.client.strategy.tosspay.TossPay;
import me.jincrates.ecommerce.payment.domain.PaymentType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PayClientConfig {
    @Bean
    public Map<PaymentType, PayClient> payClientMap() {
        Map<PaymentType, PayClient> payClientsMap = new HashMap<>();
        payClientsMap.put(PaymentType.KAKAO_PAY, new KakaoPay());
        payClientsMap.put(PaymentType.NAVER_PAY, new NaverPay());
        payClientsMap.put(PaymentType.SETTLE_PAY, new SettlePay());
        payClientsMap.put(PaymentType.TOSS_PAY, new TossPay());
        payClientsMap.put(PaymentType.PORT_ONE, new PortOne());
        return payClientsMap;
    }
}
