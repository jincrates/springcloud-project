package me.jincrates.msa.coffeekiosk.unit.calculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DayCalculatorRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("시작일을 입력하세요 (예: 2023-01-01): ");
            String startDateStr = scanner.nextLine();
            PaymentDate startDate = new PaymentDate(startDateStr);

            System.out.print("결제일을 입력하세요 (예: 2023-01-01): ");
            String paymentDateStr = scanner.nextLine();
            PaymentDate paymentDate = new PaymentDate(paymentDateStr);

            // 기본값 (M+1, D-1)
            PaymentDate endDate = paymentDate.plusMonths(1).minusDays(1);

            boolean isLeap = paymentDate.isLeap();
            if (startDate.equalsDayOfMonth(29)) {
                if (!isLeap && paymentDate.getMonth().getValue() == 3 && paymentDate.getDayOfMonth() == 1) {
                    endDate = LocalDate.of(paymentDate.getYear(), paymentDate.getMonth(), 28);
                }

                if (isLeap && paymentDate.getMonth().getValue() == 2 && paymentDate.getDayOfMonth() == 29) {
                    endDate = LocalDate.of(paymentDate.getYear(), paymentDate.getMonth().plus(1), 28);
                }
            }

            if (startDate.getDayOfMonth() == 30) {
                if (startDate.getMonth().getValue() == 1) {

                }
            }


            // 날짜 출력
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일");

            System.out.println("시작일: " + startDate.format(formatter));
            System.out.println("결제일: " + paymentDate.format(formatter) + "(" + (isLeap ? "윤년" : "평년") + ")");
            System.out.println("종료일: " + endDate.format(formatter));

            System.out.println("다시 계산하시겠습니까? (Y/N)");
            String retry = scanner.nextLine();
            if (!retry.equalsIgnoreCase("Y")) {
                break;
            }
        }
        System.out.println("이용해주셔서 감사합니다.");
    }
}
