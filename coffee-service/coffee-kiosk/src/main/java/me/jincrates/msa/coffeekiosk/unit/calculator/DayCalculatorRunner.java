package me.jincrates.msa.coffeekiosk.unit.calculator;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DayCalculatorRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("날짜를 입력하세요 (예: 2023-01-28): ");
            String dateStr = scanner.nextLine();

            LocalDate startDate = LocalDate.parse(dateStr);
            LocalDate endDate = startDate.plusMonths(1).minusDays(1);


            // 날짜 출력
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일");
            String yearType = Year.isLeap(startDate.getYear()) ? "윤년" : "평년";
            System.out.println("시작일: " + startDate.format(formatter) + "(" + yearType + ")");
            System.out.println("종료일: " + endDate.format(formatter));

            System.out.println("다시 계산하시겠습니까? (Y/N)");
            String retry = scanner.nextLine();
            if (!retry.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }
}
