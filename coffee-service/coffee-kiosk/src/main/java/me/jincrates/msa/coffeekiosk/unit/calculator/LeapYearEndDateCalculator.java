package me.jincrates.msa.coffeekiosk.unit.calculator;

import java.time.LocalDate;

public class LeapYearEndDateCalculator implements EndDateCalculatorStrategy {
    @Override
    public PaymentDate calculateEndDate(int startDay, PaymentDate paymentDate) {
        if (startDay == 29) {
            if (paymentDate.equalsDate(2, 29)) {
                return paymentDate.setDate(3, 28);
            }
        }

        if (startDay == 30) {
            if (paymentDate.equalsDate(1, 30)) {
                return paymentDate.setDate(2, 28);
            }

            if (paymentDate.equalsDate(2, 29)) {
                return paymentDate.setDate(3, 29);
            }
        }

        // 이게 좀 골때리네
        if (startDay == 31) {
            LocalDate nextDate = paymentDate.getValue().plusMonths(1);
            return PaymentDate.of(nextDate.getYear(), nextDate.getMonth(), nextDate.lengthOfMonth() - 1);
        }

        if (startDay == 1) {
            if (paymentDate.equalsDate(2, 1)) {
                return paymentDate.setDate(2, 28);
            }
            if (paymentDate.equalsDate(2, 29)) {
                return paymentDate.setDate(3, 31);
            }

            return paymentDate.plusMonths(1).minusDays(1);
        }

        return paymentDate.minusDays(1).plusMonths(1);
    }
}
