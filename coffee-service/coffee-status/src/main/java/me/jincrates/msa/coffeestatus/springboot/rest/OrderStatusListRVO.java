package me.jincrates.msa.coffeestatus.springboot.rest;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderStatusListRVO {

    private int totalCount;
    private List<OrderStatusRVO> orderStatusList;

    @Builder
    public OrderStatusListRVO(int totalCount, List<OrderStatusRVO> orderStatusList) {
        this.totalCount = totalCount;
        this.orderStatusList = orderStatusList;
    }
}
