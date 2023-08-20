package me.jincrates.msa.coffeekiosk.unit.beverage.v2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Size {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L");

    private final String text;
}
