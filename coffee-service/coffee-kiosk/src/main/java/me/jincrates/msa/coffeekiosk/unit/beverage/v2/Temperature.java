package me.jincrates.msa.coffeekiosk.unit.beverage.v2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Temperature {
    HOT("Hot"),
    COLD("Cold");

    private final String text;
}
