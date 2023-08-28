package me.jincrates.claimservice.domain.order.custom.response;

import lombok.Getter;

@Getter
public class OrderProductRepositoryResponse {

    private Long orderProductId;
    private Long orderId;
    private String name;
    private int quantity;
    private int price;

    public OrderProductRepositoryResponse(Long orderProductId, Long orderId, String name,
        int quantity, int price) {
        this.orderProductId = orderProductId;
        this.orderId = orderId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
