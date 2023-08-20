package me.jincrates.msa.coffeekiosk.unit.beverage.v2;

public class Coffee {
    private String name;
    private int basePrice;
    private int shot;
    private EnableOption enableOption;
    private SelectedOption selectedOption = new SelectedOption();

    public Coffee(String name, int basePrice, EnableOption enableOption) {
        this.name = name;
        this.basePrice = basePrice;
        this.shot = 2;
        this.enableOption = enableOption;
    }

    public String getName() {
        return name;
    }

    public EnableOption getEnableOption() {
        return enableOption;
    }

    public void size(Size size) {
        this.selectedOption.setSize(size);
    }

    public void temperature(Temperature temperature) {
        this.selectedOption.setTemperature(temperature);
    }

    public void addWhippedCream() {
        this.selectedOption.addWhippedCream();
    }

    public void addCinnamon() {
        this.selectedOption.addCinnamon();
    }

    public void makeDecaf() {
        this.selectedOption.makeDecaf();
    }

    public int calculatePrice() {
        return basePrice + selectedOption.calculatePrice();
    }

    @Override
    public String toString() {
        return name + " (" + selectedOption + ")";
    }
}
