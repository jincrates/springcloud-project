package me.jincrates.ecommerce.orders.application.service.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderListPageServiceResponse {

    private int pageNo;
    private boolean hasNext;
    private List<OrderServiceResponse> contents;

    @Builder
    private OrderListPageServiceResponse(int pageNo, boolean hasNext,
        List<OrderServiceResponse> contents) {
        this.pageNo = pageNo;
        this.hasNext = hasNext;
        this.contents = contents;
    }
}
