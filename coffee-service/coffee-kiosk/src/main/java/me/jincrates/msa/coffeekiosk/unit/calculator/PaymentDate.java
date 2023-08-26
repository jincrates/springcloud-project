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
}
