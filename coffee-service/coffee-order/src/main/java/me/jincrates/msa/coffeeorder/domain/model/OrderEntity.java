package me.jincrates.msa.coffeeorder.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderEntity {

    private String id;
    private String orderNumber; // 주문번호
    private String coffeeName;  // 커피종류
    private String coffeeCount; // 커피개수
    private String customerName; // 회원명

    @Builder
    public OrderEntity(String id, String orderNumber, String coffeeName, String coffeeCount,
        String customerName) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.coffeeName = coffeeName;
        this.coffeeCount = coffeeCount;
        this.customerName = customerName;
    }
}
