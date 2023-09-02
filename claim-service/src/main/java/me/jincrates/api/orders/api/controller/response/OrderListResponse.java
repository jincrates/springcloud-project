package me.jincrates.api.orders.api.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderListResponse {

    private Long orderId;
    private LocalDateTime orderedAt;
    private PageCommonResponse<OrderProductListResponse> orderProductList;

    @Builder
    private OrderListResponse(Long orderId, LocalDateTime orderedAt,
                              PageCommonResponse<OrderProductListResponse> orderProductList) {
        this.orderId = orderId;
        this.orderedAt = orderedAt;
        this.orderProductList = orderProductList;
    }
}
