package me.jincrates.msa.coffeekiosk.unit.calculator;

import lombok.Getter;
import me.jincrates.msa.coffeekiosk.unit.calculator.strategy.CommonYearEndDateCalculator;
import me.jincrates.msa.coffeekiosk.unit.calculator.strategy.EndDateCalculatorStrategy;
import me.jincrates.msa.coffeekiosk.unit.calculator.strategy.LeapYearEndDateCalculator;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Getter
public class PaymentDate {
    private final LocalDate value;
    private final boolean isLeap;
    private final EndDateCalculatorStrategy strategy;

    private PaymentDate(LocalDate date) {
        this.value = date;
        this.isLeap = Year.isLeap(date.getYear());
        this.strategy = isLeap
                ? new LeapYearEndDateCalculator()
                : new CommonYearEndDateCalculator();
    }

    public static PaymentDate from(LocalDate date) {
        return new PaymentDate(date);
    }

    public static PaymentDate from(String dateStr) {
        return new PaymentDate(LocalDate.parse(dateStr));
    }

    public static PaymentDate of(int year, int month, int day) {
        return new PaymentDate(LocalDate.of(year, Month.of(month), day));
    }

    public int getYear() {
        return this.value.getYear();
    }

    public int getDayOfMonth() {
        return this.value.getDayOfMonth();
    }

    public PaymentDate minusOneLastDateOfNextMonth() {
        LocalDate nextDate = this.getValue().plusMonths(1);
        return PaymentDate.of(nextDate.getYear(), nextDate.getMonth().getValue(), nextDate.lengthOfMonth() - 1);
    }

    public PaymentDate plusMonths(int month) {
        return new PaymentDate(this.value.plusMonths(month));
    }

    public PaymentDate minusDays(int days) {
        return new PaymentDate(this.value.minusDays(days));
    }

    public boolean equalsDate(int month, int dayOfMonth) {
        return this.value.getMonth().getValue() == month
                && this.value.getDayOfMonth() == dayOfMonth;
    }

    public PaymentDate calculateEndDate(int startDay) {
        return strategy.calculateEndDate(startDay, this);
    }
}
