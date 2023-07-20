package me.jincrates.msa.coffeekiosk.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CoffeeKioskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeKioskApplication.class, args);
    }

}
