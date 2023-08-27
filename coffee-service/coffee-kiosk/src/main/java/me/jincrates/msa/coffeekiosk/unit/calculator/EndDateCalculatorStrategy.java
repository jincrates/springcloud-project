package me.jincrates.msa.coffeekiosk.unit.calculator;

public interface EndDateCalculatorStrategy {
    PaymentDate calculateEndDate(int startDay, PaymentDate paymentDate);
}
