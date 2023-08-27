package me.jincrates.msa.coffeekiosk.unit.calculator.strategy;

import me.jincrates.msa.coffeekiosk.unit.calculator.PaymentDate;

public class LeapYearEndDateCalculator implements EndDateCalculatorStrategy {
    @Override
    public PaymentDate calculateEndDate(int startDay, PaymentDate paymentDate) {
        return switch (startDay) {
            case 29 -> handleStartDay29(paymentDate);
            case 30 -> handleStartDay30(paymentDate);
            case 31 -> handleStartDay31(paymentDate);
            case 1 -> handleStartDay1(paymentDate);
            default -> defaultEndDateCalculation(paymentDate);
        };
    }

    private static PaymentDate defaultEndDateCalculation(PaymentDate paymentDate) {
        return paymentDate.minusDays(1).plusMonths(1);
    }

    private PaymentDate handleStartDay29(PaymentDate paymentDate) {
        if (paymentDate.equalsDate(2, 29)) {
            return PaymentDate.of(paymentDate.getYear(), 3, 28);
        }

        return defaultEndDateCalculation(paymentDate);
    }

    private PaymentDate handleStartDay30(PaymentDate paymentDate) {
        if (paymentDate.equalsDate(1, 30)) {
            return PaymentDate.of(paymentDate.getYear(), 2, 28);
        }

        if (paymentDate.equalsDate(2, 29)) {
            return PaymentDate.of(paymentDate.getYear(), 3, 29);
        }

        return defaultEndDateCalculation(paymentDate);
    }

    private PaymentDate handleStartDay31(PaymentDate paymentDate) {
        return paymentDate.minusOneLastDateOfNextMonth();
    }

    private PaymentDate handleStartDay1(PaymentDate paymentDate) {
        if (paymentDate.equalsDate(2, 1)) {
            return PaymentDate.of(paymentDate.getYear(), 2, 28);
        }
        if (paymentDate.equalsDate(2, 29)) {
            return PaymentDate.of(paymentDate.getYear(), 3, 31);
        }

        return paymentDate.plusMonths(1).minusDays(1);
    }
}
