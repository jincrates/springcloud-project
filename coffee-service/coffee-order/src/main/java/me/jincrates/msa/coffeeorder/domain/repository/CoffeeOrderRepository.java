package me.jincrates.msa.coffeeorder.domain.repository;

import me.jincrates.msa.coffeeorder.domain.model.OrderEntity;

public interface CoffeeOrderRepository {

    String saveCoffeeOrder(OrderEntity orderEntity);
}
