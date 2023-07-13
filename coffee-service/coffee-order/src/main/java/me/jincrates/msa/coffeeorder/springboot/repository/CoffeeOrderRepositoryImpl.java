package me.jincrates.msa.coffeeorder.springboot.repository;

import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeeorder.domain.model.OrderEntity;
import me.jincrates.msa.coffeeorder.domain.repository.CoffeeOrderRepository;
import me.jincrates.msa.coffeeorder.springboot.repository.jpa.CoffeeOrderJpaRepository;
import me.jincrates.msa.coffeeorder.springboot.repository.jpa.OrderJpaEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CoffeeOrderRepositoryImpl implements CoffeeOrderRepository {

    private final CoffeeOrderJpaRepository coffeeOrderJpaRepository;

    @Override
    public String saveCoffeeOrder(OrderEntity orderEntity) {

        OrderJpaEntity orderJpaEntity = OrderJpaEntity.builder()
            .orderNumber(orderEntity.getOrderNumber())
            .coffeeName(orderEntity.getCoffeeName())
            .coffeeCount(orderEntity.getCoffeeCount())
            .customerName(orderEntity.getCustomerName())
            .build();

        coffeeOrderJpaRepository.save(orderJpaEntity);

        return orderEntity.getId();
    }
}
