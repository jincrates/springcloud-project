package me.jincrates.msa.coffeeorder.domain.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeeorder.domain.model.CoffeeOrderCVO;
import me.jincrates.msa.coffeeorder.domain.model.OrderEntity;
import me.jincrates.msa.coffeeorder.domain.repository.CoffeeOrderRepository;

@RequiredArgsConstructor
public class CoffeeOrderImpl implements CoffeeOrder {

    private final CoffeeOrderRepository coffeeOrderRepository;

    @Override
    public String orderCoffee(CoffeeOrderCVO coffeeOrderCVO) {

        OrderEntity orderEntity = OrderEntity.builder()
            .orderNumber(coffeeOrderCVO.getOrderNumber())
            .coffeeName(coffeeOrderCVO.getCoffeeName())
            .coffeeCount(coffeeOrderCVO.getCoffeeCount())
            .customerName(coffeeOrderCVO.getCustomerName())
            .build();

        coffeeOrderRepository.saveCoffeeOrder(orderEntity);

        return orderEntity.getId();
    }
}
