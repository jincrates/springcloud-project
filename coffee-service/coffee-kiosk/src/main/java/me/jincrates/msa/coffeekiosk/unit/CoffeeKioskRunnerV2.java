package me.jincrates.msa.coffeekiosk.unit;

import me.jincrates.msa.coffeekiosk.unit.beverage.v2.Coffee;
import me.jincrates.msa.coffeekiosk.unit.beverage.v2.Size;
import me.jincrates.msa.coffeekiosk.unit.beverage.v2.Temperature;

import java.util.Scanner;

public class CoffeeKioskRunnerV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Coffee americano = new Coffee("아메리카노", 3000);
        Coffee latte = new Coffee("라떼", 4000);
        Coffee espresso = new Coffee("에스프레소", 2500);

        System.out.println("커피 주문 프로그램에 오신 것을 환영합니다!");

        System.out.println("커피 종류를 선택하세요:");
        System.out.println("1. 아메리카노");
        System.out.println("2. 라떼");
        System.out.println("3. 에스프레소");
        int choice = scanner.nextInt();

        Coffee selectedCoffee = null;
        switch (choice) {
            case 1 -> selectedCoffee = americano;
            case 2 -> selectedCoffee = latte;
            case 3 -> selectedCoffee = espresso;
            default -> {
                System.out.println("올바른 선택이 아닙니다.");
                System.exit(0);
            }
        }

        System.out.println("휘핑크림 추가하시겠습니까? (Y/N)");
        String whippedCreamChoice = scanner.next();
        if (whippedCreamChoice.equalsIgnoreCase("Y")) {
            selectedCoffee.addWhippedCream();
        }

        System.out.println("시나몬 추가하시겠습니까? (Y/N)");
        String cinnamonChoice = scanner.next();
        if (cinnamonChoice.equalsIgnoreCase("Y")) {
            selectedCoffee.addCinnamon();
        }

        System.out.println("디카페인 여부를 선택하세요 (Y/N)");
        String decafChoice = scanner.next();
        if (decafChoice.equalsIgnoreCase("Y")) {
            selectedCoffee.makeDecaf();
        }

        System.out.println("커피 사이즈를 선택하세요:");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");
        int sizeChoice = scanner.nextInt();
        switch (sizeChoice) {
            case 1 -> selectedCoffee.setSize(Size.SMALL);
            case 2 -> selectedCoffee.setSize(Size.MEDIUM);
            case 3 -> selectedCoffee.setSize(Size.LARGE);
            default -> {
                System.out.println("올바른 선택이 아닙니다.");
                System.exit(0);
            }
        }

        System.out.println("커피 온도를 선택하세요:");
        System.out.println("1. Hot");
        System.out.println("2. Cold");
        int temperatureChoice = scanner.nextInt();
        switch (temperatureChoice) {
            case 1 -> selectedCoffee.setTemperature(Temperature.HOT);
            case 2 -> selectedCoffee.setTemperature(Temperature.COLD);
            default -> {
                System.out.println("올바른 선택이 아닙니다.");
                System.exit(0);
            }
        }

        System.out.println("주문 내역:");
        System.out.println(selectedCoffee.toString());
        System.out.println("가격: " + selectedCoffee.calculatePrice() + "원");

        scanner.close();
    }
}
