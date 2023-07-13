package me.jincrates.msa.coffeeorder.domain.service;

import me.jincrates.msa.coffeeorder.domain.model.CoffeeOrderCVO;

public interface CoffeeOrder {

    String orderCoffee(CoffeeOrderCVO coffeeOrderCVO);
}
