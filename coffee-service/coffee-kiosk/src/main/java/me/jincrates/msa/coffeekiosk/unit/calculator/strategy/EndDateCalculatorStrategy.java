package me.jincrates.msa.coffeekiosk.unit.calculator.strategy;

import me.jincrates.msa.coffeekiosk.unit.calculator.PaymentDate;

public interface EndDateCalculatorStrategy {
    PaymentDate calculateEndDate(int startDay, PaymentDate paymentDate);
}
