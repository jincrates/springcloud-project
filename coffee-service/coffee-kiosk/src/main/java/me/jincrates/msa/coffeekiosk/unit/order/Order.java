package me.jincrates.msa.coffeekiosk.unit.order;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.unit.beverage.Beverage;

@Getter
@RequiredArgsConstructor
public class Order {

    private final LocalDateTime orderedAt;
    private final List<Beverage> beverages;
}
