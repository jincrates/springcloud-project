package me.jincrates.msa.coffeekiosk.unit.beverage.v2;

import java.util.List;

public class EnableOption {
    private List<Temperature> temperatures;
    private List<Size> sizes;
    private boolean whippedCream;
    private boolean cinnamon;
    private boolean decaf;

    public EnableOption(List<Temperature> temperatures, List<Size> sizes, boolean whippedCream, boolean cinnamon, boolean decaf) {
        this.temperatures = temperatures;
        this.sizes = sizes;
        this.whippedCream = whippedCream;
        this.cinnamon = cinnamon;
        this.decaf = decaf;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public boolean isWhippedCream() {
        return whippedCream;
    }

    public boolean isCinnamon() {
        return cinnamon;
    }

    public boolean isDecaf() {
        return decaf;
    }
}
