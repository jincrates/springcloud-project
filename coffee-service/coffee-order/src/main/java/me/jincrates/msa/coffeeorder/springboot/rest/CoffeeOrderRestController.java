package me.jincrates.msa.coffeeorder.springboot.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeeorder.domain.model.CoffeeOrderCVO;
import me.jincrates.msa.coffeeorder.springboot.messageq.KafkaProducer;
import me.jincrates.msa.coffeeorder.springboot.service.CoffeeOrderService;
import me.jincrates.msa.coffeeorder.springboot.service.MsaServiceCoffeeMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CoffeeOrderRestController {

    private final CoffeeOrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final MsaServiceCoffeeMember msaServiceCoffeeMember;

    @PostMapping("/coffee-order")
    public ResponseEntity<CoffeeOrderCVO> orderCoffee(@RequestBody CoffeeOrderCVO coffeeOrderCVO) {
        // is member
        if (msaServiceCoffeeMember.existsByMemberName(coffeeOrderCVO.getCoffeeName())) {
            log.info("{} is a member!", coffeeOrderCVO.getCoffeeName());
        }

        // coffee order
        orderService.orderCoffee(coffeeOrderCVO);

        // kafka
        kafkaProducer.send("jincrates-kafka-test", coffeeOrderCVO);

        return new ResponseEntity<CoffeeOrderCVO>(coffeeOrderCVO, HttpStatus.OK);
    }
}
