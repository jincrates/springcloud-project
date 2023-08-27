package me.jincrates.msa.coffeekiosk.unit.calculator;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Getter
public class PaymentDate {
    private final LocalDate value;
    private final boolean isLeap;

    public PaymentDate(LocalDate date) {
        this.value = date;
        this.isLeap = Year.isLeap(date.getYear());
    }

    public PaymentDate(String dateStr) {
        this.value = LocalDate.parse(dateStr);
        this.isLeap = Year.isLeap(this.value.getYear());
    }

    public static PaymentDate of(int year, Month month, int day) {
        return new PaymentDate(LocalDate.of(year, month, day));
    }

    public int getYear() {
        return this.value.getYear();
    }

    public int getDayOfMonth() {
        return this.value.getDayOfMonth();
    }

    public PaymentDate plusMonths(int month) {
        return new PaymentDate(this.value.plusMonths(month));
    }

    public PaymentDate minusDays(int month) {
        return new PaymentDate(this.value.minusDays(month));
    }

    public boolean equalsDate(int month, int dayOfMonth) {
        return this.value.getMonth().getValue() == month
                && this.value.getDayOfMonth() == dayOfMonth;
    }

    public static PaymentDate calculateEndDate(int startDay, PaymentDate paymentDate) {
        EndDateCalculatorStrategy strategy = paymentDate.isLeap
                ? new LeapYearEndDateCalculator()
                : new CommonYearEndDateCalculator();
        return strategy.calculateEndDate(startDay, paymentDate);
    }

    PaymentDate setDate(int month, int dayOfMonth) {
        return PaymentDate.of(this.getYear(), Month.of(month), dayOfMonth);
    }
}
