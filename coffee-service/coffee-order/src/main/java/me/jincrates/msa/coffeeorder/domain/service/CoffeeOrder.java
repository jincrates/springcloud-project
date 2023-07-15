package me.jincrates.msa.coffeeorder.domain.service;

import me.jincrates.msa.coffeeorder.domain.model.CoffeeOrderCVO;

public interface CoffeeOrder {

    CoffeeOrderCVO orderCoffee(CoffeeOrderCVO coffeeOrderCVO);
}
