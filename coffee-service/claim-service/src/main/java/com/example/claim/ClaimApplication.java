package com.example.claim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClaimApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClaimApplication.class, args);
    }
}
