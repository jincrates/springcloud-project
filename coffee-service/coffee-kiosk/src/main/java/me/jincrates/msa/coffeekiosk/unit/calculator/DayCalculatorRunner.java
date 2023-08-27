package me.jincrates.msa.coffeekiosk.unit.calculator;

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

            // 종료일 계산
            PaymentDate endDate = PaymentDate.calculateEndDate(startDate.getDayOfMonth(), paymentDate);

            // 날짜 출력
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일");


            System.out.println("시작일: " + startDate.getValue().format(formatter));
            System.out.println("결제일: " + paymentDate.getValue().format(formatter) + "(" + (paymentDate.isLeap() ? "윤년" : "평년") + ")");
            System.out.println("종료일: " + endDate.getValue().format(formatter));

            System.out.println("다시 계산하시겠습니까? (Y/N)");
            String retry = scanner.nextLine();
            if (!retry.equalsIgnoreCase("Y")) {
                break;
            }
        }
        System.out.println("이용해주셔서 감사합니다.");
    }
}
