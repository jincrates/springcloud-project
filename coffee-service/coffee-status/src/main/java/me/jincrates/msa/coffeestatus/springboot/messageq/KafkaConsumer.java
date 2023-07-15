package me.jincrates.msa.coffeestatus.springboot.messageq;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeestatus.springboot.repository.jpa.CoffeeStatusJpaRepository;
import me.jincrates.msa.coffeestatus.springboot.repository.jpa.OrderStatusJpaEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CoffeeStatusJpaRepository coffeeStatusJpaRepository;

    // TODO: 공통모듈로 뽑아내기
    @KafkaListener(topics = "jincrates-kafka-test")
    public void processMessage(String kafkaMessage) {
        log.info("Kafka Message : =====> {}", kafkaMessage);

        OrderStatusJpaEntity orderStatusJpaEntity = OrderStatusJpaEntity.builder()
            .id(UUID.randomUUID())
            .orderHistory(kafkaMessage)
            .build();

        coffeeStatusJpaRepository.save(orderStatusJpaEntity);
    }
}
