package me.jincrates.claimservice.api.controller.order.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

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
