package me.jincrates.msa.coffeeorder.springboot.messageq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.msa.coffeeorder.domain.model.CoffeeOrderCVO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CoffeeOrderCVO send(String topic, CoffeeOrderCVO coffeeOrderCVO) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(coffeeOrderCVO);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer send data from the Order microservice: {}", coffeeOrderCVO);

        return coffeeOrderCVO;
    }
}