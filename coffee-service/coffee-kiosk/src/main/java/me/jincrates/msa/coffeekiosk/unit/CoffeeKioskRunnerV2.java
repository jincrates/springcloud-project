package me.jincrates.msa.coffeekiosk.unit;

import me.jincrates.msa.coffeekiosk.unit.beverage.v2.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeKioskRunnerV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 웰컴 메시지
        System.out.println("커피 주문 프로그램에 오신 것을 환영합니다!");

        Menu menu = createMenu();
        List<Coffee> cart = new ArrayList<>();
        while (true) {
            // 커피 메뉴 생성
            System.out.println("커피 종류를 선택하세요:");
            for (int i = 1; i <= menu.getList().size(); i++) {
                System.out.println(i + ". " + menu.getList().get(i - 1).getName());
            }

            // 메뉴 선택
            int choice = scanner.nextInt();
            Coffee selectedCoffee = menu.getList().get(choice - 1);

            // 카트 담기
            cart.add(selectedCoffee);

            // 메뉴 옵션 선택
            selectedMenuOption(scanner, selectedCoffee);

            System.out.println("추가로 더 주문하시겠습니까? (Y/N)");
            String addedChoice = scanner.next();
            if (!addedChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        // 주문내역 출력
        printHistory(cart);

        scanner.close();
    }

    private static void printHistory(List<Coffee> cart) {
        int totalPrice = 0;

        String line = "==============================================";
        System.out.println(line);
        System.out.println("주문 내역:");
        for (Coffee coffee : cart) {
            totalPrice += coffee.calculatePrice();
            System.out.println(coffee + " --- " + coffee.calculatePrice() + "원");
        }

        System.out.println("총 가격: " + totalPrice + "원");

    }

    private static void selectedMenuOption(Scanner scanner, Coffee selectedCoffee) {
        System.out.println("커피 온도를 선택하세요:");
        List<Temperature> temperatureList = selectedCoffee.getEnableOption().getTemperatures();
        for (int i = 1; i <= temperatureList.size(); i++) {
            System.out.println(i + ". " + temperatureList.get(i - 1).getText());
        }
        int temperatureChoice = scanner.nextInt();
        Temperature selectedTemperature = temperatureList.get(temperatureChoice - 1);
        selectedCoffee.temperature(selectedTemperature);

        System.out.println("커피 사이즈를 선택하세요:");
        List<Size> sizeList = selectedCoffee.getEnableOption().getSizes();
        for (int i = 1; i <= sizeList.size(); i++) {
            System.out.println(i + ". " + sizeList.get(i - 1).getText());
        }
        int sizeChoice = scanner.nextInt();
        Size selectedSize = sizeList.get(sizeChoice - 1);
        selectedCoffee.size(selectedSize);

        if (selectedCoffee.getEnableOption().isWhippedCream()) {
            System.out.println("휘핑크림 추가하시겠습니까? (Y/N)");
            String whippedCreamChoice = scanner.next();
            if (whippedCreamChoice.equalsIgnoreCase("Y")) {
                selectedCoffee.addWhippedCream();
            }
        }

        if (selectedCoffee.getEnableOption().isCinnamon()) {
            System.out.println("시나몬 추가하시겠습니까? (Y/N)");
            String cinnamonChoice = scanner.next();
            if (cinnamonChoice.equalsIgnoreCase("Y")) {
                selectedCoffee.addCinnamon();
            }
        }

        if (selectedCoffee.getEnableOption().isDecaf()) {
            System.out.println("디카페인 여부를 선택하세요 (Y/N)");
            String decafChoice = scanner.next();
            if (decafChoice.equalsIgnoreCase("Y")) {
                selectedCoffee.makeDecaf();
            }
        }
    }

    private static Menu createMenu() {
        Coffee americano = new Coffee("아메리카노", 3000,
                new EnableOption(
                        List.of(Temperature.HOT, Temperature.COLD),
                        List.of(Size.SMALL, Size.MEDIUM, Size.LARGE),
                        false,
                        false,
                        true
                ));
        Coffee latte = new Coffee("라떼", 4000,
                new EnableOption(
                        List.of(Temperature.HOT, Temperature.COLD),
                        List.of(Size.SMALL, Size.MEDIUM, Size.LARGE),
                        false,
                        true,
                        true
                ));
        Coffee mocha = new Coffee("모카", 4500,
                new EnableOption(
                        List.of(Temperature.HOT, Temperature.COLD),
                        List.of(Size.SMALL, Size.MEDIUM, Size.LARGE),
                        true,
                        false,
                        true
                ));
        Coffee espresso = new Coffee("에스프레소", 2500,
                new EnableOption(
                        List.of(Temperature.HOT),
                        List.of(Size.SMALL),
                        false,
                        false,
                        true
                ));
        Coffee iceTea = new Coffee("아이스티", 2500,
                new EnableOption(
                        List.of(Temperature.COLD),
                        List.of(Size.MEDIUM),
                        false,
                        false,
                        false
                ));

        return new Menu(List.of(americano, latte, mocha, espresso, iceTea));
    }
}
