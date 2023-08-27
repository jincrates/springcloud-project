package me.jincrates.msa.coffeekiosk.unit.calculator;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Getter
public class PaymentDate {
    private final LocalDate date;
    private final boolean isLeap;

    public PaymentDate(LocalDate inputDate) {
        this.date = inputDate;
        this.isLeap = Year.isLeap(inputDate.getYear());
    }

    public PaymentDate(String dateStr) {
        this.date = LocalDate.parse(dateStr);
        this.isLeap = Year.isLeap(this.date.getYear());
    }

    public static PaymentDate of(int year, Month month, int day) {
        return new PaymentDate(LocalDate.of(year, month, day));
    }

    public int getYear() {
        return this.date.getYear();
    }

    public Month getMonth() {
        return this.date.getMonth();
    }

    public PaymentDate plusMonths(int month) {
        return new PaymentDate(this.date.plusMonths(month));
    }

    public PaymentDate minusDays(int month) {
        return new PaymentDate(this.date.minusDays(month));
    }

    public boolean equalsDayOfMonth(int dayOfMonth) {
        return this.date.getDayOfMonth() == dayOfMonth;
    }

    public boolean equalsDate(int month, int dayOfMonth) {
        return this.date.getMonth().getValue() == month
                && this.date.getDayOfMonth() == dayOfMonth;
    }

    public static PaymentDate calculateEndDate(PaymentDate startDate, PaymentDate paymentDate) {
        boolean isLeap = paymentDate.isLeap();

        if (startDate.equalsDayOfMonth(29)) {
            // 윤년
            if (isLeap && paymentDate.equalsDate(2, 29)) {
                return paymentDate.setDate(3, 28);
            }

            // 평년
            if (!isLeap && paymentDate.equalsDate(3, 1)) {
                return paymentDate.setDate(3, 28);
            }
        }

        if (startDate.equalsDayOfMonth(30)) {
            // 윤년
            if (isLeap) {
                if (paymentDate.equalsDate(1, 30)) {
                    return paymentDate.setDate(2, 28);
                }

                if (paymentDate.equalsDate(2, 29)) {
                    return paymentDate.setDate(3, 29);
                }
            }

            // 평년
            if (!isLeap && paymentDate.equalsDate(3, 1)) {
                return paymentDate.setDate(3, 29);
            }
        }

        // 이게 좀 골때리네
        if (startDate.equalsDayOfMonth(31)) {
            // 평년
            if (!isLeap) {
                if (paymentDate.equalsDate(1, 31)) {
                    return paymentDate.setDate(2, 28);
                }
                if (paymentDate.equalsDate(3, 1)) {
                    return paymentDate.setDate(3, 30);
                }
            }
            LocalDate nextDate = paymentDate.getDate().plusMonths(1);
            return PaymentDate.of(nextDate.getYear(), nextDate.getMonth(), nextDate.lengthOfMonth() - 1);
        }

        if (startDate.equalsDayOfMonth(1)) {
            // 윤년
            if (isLeap) {
                if (paymentDate.equalsDate(2, 1)) {
                    return paymentDate.setDate(2, 28);
                }
                if (paymentDate.equalsDate(2, 29)) {
                    return paymentDate.setDate(3, 31);
                }

            }

            return paymentDate.plusMonths(1).minusDays(1);
        }

        // 기본값
        return paymentDate.minusDays(1).plusMonths(1);
    }

    private PaymentDate setDate(int month, int dayOfMonth) {
        return PaymentDate.of(this.getYear(), Month.of(month), dayOfMonth);
    }
}
