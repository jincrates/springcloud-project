package me.jincrates.ecommerce.payment.config;

import me.jincrates.ecommerce.payment.adapter.client.strategy.PayClient;
import me.jincrates.ecommerce.payment.adapter.client.strategy.kakaopay.KakaoPay;
import me.jincrates.ecommerce.payment.adapter.client.strategy.naverpay.NaverPay;
import me.jincrates.ecommerce.payment.adapter.client.strategy.portone.PortOne;
import me.jincrates.ecommerce.payment.adapter.client.strategy.settlepay.SettlePay;
import me.jincrates.ecommerce.payment.adapter.client.strategy.tosspay.TossPay;
import me.jincrates.ecommerce.payment.domain.PaymentMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PayClientConfig {
    @Bean
    public Map<PaymentMethod, PayClient> payClientMap() {
        Map<PaymentMethod, PayClient> payClientsMap = new HashMap<>();
        payClientsMap.put(PaymentMethod.KAKAO_PAY, new KakaoPay());
        payClientsMap.put(PaymentMethod.NAVER_PAY, new NaverPay());
        payClientsMap.put(PaymentMethod.SETTLE_PAY, new SettlePay());
        payClientsMap.put(PaymentMethod.TOSS_PAY, new TossPay());
        payClientsMap.put(PaymentMethod.PORT_ONE, new PortOne());
        return payClientsMap;
    }
}
