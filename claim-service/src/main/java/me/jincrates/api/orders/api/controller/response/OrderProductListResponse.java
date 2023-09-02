package me.jincrates.api.orders.api.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductListResponse {

    private Long orderProductId;
    private String status;
    private String name;
    private String option;
    private Integer price;
    private Integer quantity;

    @Builder
    private OrderProductListResponse(Long orderProductId, String status, String name,
                                     String option, Integer price, Integer quantity) {
        this.orderProductId = orderProductId;
        this.status = status;
        this.name = name;
        this.option = option;
        this.price = price;
        this.quantity = quantity;
    }
}
