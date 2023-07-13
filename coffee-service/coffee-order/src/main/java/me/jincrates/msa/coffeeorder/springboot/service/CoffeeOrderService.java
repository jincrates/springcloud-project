package me.jincrates.msa.coffeeorder.springboot.service;

import me.jincrates.msa.coffeeorder.domain.repository.CoffeeOrderRepository;
import me.jincrates.msa.coffeeorder.domain.service.CoffeeOrderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoffeeOrderService extends CoffeeOrderImpl {

    public CoffeeOrderService(CoffeeOrderRepository coffeeOrderRepository) {
        super(coffeeOrderRepository);
    }
}
