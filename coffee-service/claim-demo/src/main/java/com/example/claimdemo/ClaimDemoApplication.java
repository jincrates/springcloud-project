package com.example.claimdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClaimDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClaimDemoApplication.class, args);
    }
}
