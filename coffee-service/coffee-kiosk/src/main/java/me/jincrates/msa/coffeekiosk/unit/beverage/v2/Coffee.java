package me.jincrates.msa.coffeekiosk.unit.beverage.v2;

public class Coffee {
    private String name;
    private int basePrice;
    private boolean hasWhippedCream;
    private boolean hasCinnamon;
    private boolean isDecaf;
    private Size size;
    private Temperature temperature;

    public Coffee(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
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

    public void setSize(Size size) {
        this.size = size;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public double calculatePrice() {
        double totalPrice = basePrice;

        if (hasWhippedCream) {
            totalPrice += 500;
        }
        if (hasCinnamon) {
            totalPrice += 0;
        }
        if (isDecaf) {
            totalPrice += 500;
        }
        if (size == Size.MEDIUM) {
            totalPrice += 500;
        }
        if (size == Size.LARGE) {
            totalPrice += 1000;
        }
        if (temperature == Temperature.COLD) {
            totalPrice += 500;
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        return name + " (" + size + ", " + temperature + ")";
    }
}
