package me.jincrates.msa.coffeeorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CoffeeOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeOrderApplication.class, args);
    }

}
