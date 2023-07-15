package me.jincrates.msa.coffeestatus.springboot.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeestatus.springboot.repository.jpa.CoffeeStatusJpaRepository;
import me.jincrates.msa.coffeestatus.springboot.repository.jpa.OrderStatusJpaEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoffeeOrderStatusRestController {

    private final CoffeeStatusJpaRepository coffeeStatusJpaRepository;

    @GetMapping("/order-status")
    public ResponseEntity<OrderStatusListRVO> coffeeOrderStatus() {
        List<OrderStatusJpaEntity> orderStatusJpaEntities = coffeeStatusJpaRepository.findAll();

        OrderStatusListRVO orderStatusListRVO = OrderStatusListRVO.builder()
            .totalCount(orderStatusJpaEntities.size())
            .orderStatusList(
                orderStatusJpaEntities.stream().map(entity ->
                    OrderStatusRVO.builder()
                        .id(entity.getId().toString())
                        .orderHistory(entity.getOrderHistory())
                        .build()
                ).toList()
            )
            .build();

        return new ResponseEntity<>(orderStatusListRVO, HttpStatus.OK);
    }
}
