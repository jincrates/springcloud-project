package me.jincrates.msa.coffeekiosk.unit.calculator;

import java.time.LocalDate;
import java.time.Year;

/**
 * 날짜 계산기
 */
public class DayCalculator {

    public LocalDate calculate(LocalDate firstPaidDay) {
        // 최초 결제일자
        firstPaidDay = LocalDate.of(2023, 1, 28);
        // 최근 결제일자
        LocalDate latePaidDay = LocalDate.of(2023, 6, 28);
        int year = latePaidDay.getYear();

        // 2-28
        if (firstPaidDay.getDayOfMonth() == 28) {
            return latePaidDay.plusMonths(1).minusDays(1);
        }
        // 29
        if (firstPaidDay.getDayOfMonth() == 29) {
            if (Year.isLeap(year)) {

            }

        }


        return null;
    }
}
