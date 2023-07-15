package me.jincrates.msa.coffeeorder.domain.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeeorder.domain.model.CoffeeOrderCVO;
import me.jincrates.msa.coffeeorder.domain.model.OrderEntity;
import me.jincrates.msa.coffeeorder.domain.repository.CoffeeOrderRepository;

@RequiredArgsConstructor
public class CoffeeOrderImpl implements CoffeeOrder {

    private final CoffeeOrderRepository coffeeOrderRepository;

    @Override
    public CoffeeOrderCVO orderCoffee(CoffeeOrderCVO coffeeOrderCVO) {

        OrderEntity orderEntity = OrderEntity.builder()
            .id(UUID.randomUUID().toString())
            .orderNumber(coffeeOrderCVO.getOrderNumber())
            .coffeeName(coffeeOrderCVO.getCoffeeName())
            .coffeeCount(coffeeOrderCVO.getCoffeeCount())
            .customerName(coffeeOrderCVO.getCustomerName())
            .build();

        coffeeOrderRepository.saveCoffeeOrder(orderEntity);

        return CoffeeOrderCVO.builder()
            .id(orderEntity.getId())
            .orderNumber(orderEntity.getOrderNumber())
            .coffeeName(orderEntity.getCoffeeName())
            .coffeeCount(orderEntity.getCoffeeCount())
            .customerName(orderEntity.getCustomerName())
            .build();
    }
}
