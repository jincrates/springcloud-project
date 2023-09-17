package me.jincrates.ecommerce.payment.adapter.client.strategy.portone;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.payment.adapter.client.strategy.PayClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PortOne implements PayClient {
    @Override
    public void prepare() {

    }

    @Override
    public void approve() {

    }

    @Override
    public void status() {

    }

    @Override
    public void cancel() {

    }
}
