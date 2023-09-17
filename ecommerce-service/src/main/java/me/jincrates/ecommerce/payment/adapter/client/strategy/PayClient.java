package me.jincrates.ecommerce.payment.adapter.client.strategy;

public interface PayClient {
    
    void prepare();

    void approve();

    void status();

    void cancel();
}
