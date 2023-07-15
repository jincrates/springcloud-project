package me.jincrates.msa.coffeeorder.springboot.repository.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    private UUID id;
    private String orderNumber; // 주문번호
    private String coffeeName;  // 커피종류
    private String coffeeCount; // 커피개수
    private String customerName; // 회원명
}
