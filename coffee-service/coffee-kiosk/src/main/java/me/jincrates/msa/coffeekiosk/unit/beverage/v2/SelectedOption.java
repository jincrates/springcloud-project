package me.jincrates.msa.coffeekiosk.unit.beverage.v2;

import java.util.ArrayList;
import java.util.List;

public class SelectedOption {
    private boolean hasWhippedCream;
    private boolean hasCinnamon;
    private boolean isDecaf;
    private Size size;
    private Temperature temperature;

    @Override
    public String toString() {
        List<String> options = new ArrayList<>();
        options.add(size.toString());
        options.add(temperature.toString());
        if (hasWhippedCream) {
            options.add("휘핑 추가");
        }
        if (hasCinnamon) {
            options.add("시나몬 추가");
        }
        if (isDecaf) {
            options.add("디카페인");
        }
        return String.join(",", options);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public void addWhippedCream() {
        hasWhippedCream = true;
    }

    public void addCinnamon() {
        hasCinnamon = true;
    }

    public void makeDecaf() {
        isDecaf = true;
    }

    public int calculatePrice() {
        int addedPrice = 0;

        if (hasWhippedCream) {
            addedPrice += 500;
        }
        if (hasCinnamon) {
            addedPrice += 200;
        }
        if (isDecaf) {
            addedPrice += 500;
        }
        if (size == Size.MEDIUM) {
            addedPrice += 500;
        }
        if (size == Size.LARGE) {
            addedPrice += 1000;
        }
        if (temperature == Temperature.COLD) {
            addedPrice += 500;
        }

        return addedPrice;
    }
}
