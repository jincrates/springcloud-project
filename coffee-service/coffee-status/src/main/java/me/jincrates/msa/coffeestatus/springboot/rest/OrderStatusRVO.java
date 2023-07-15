package me.jincrates.msa.coffeestatus.springboot.rest;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderStatusRVO {

    private String id;
    private String orderHistory;

    @Builder
    public OrderStatusRVO(String id, String orderHistory) {
        this.id = id;
        this.orderHistory = orderHistory;
    }
}
