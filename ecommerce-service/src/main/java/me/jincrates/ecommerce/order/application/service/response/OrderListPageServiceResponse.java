package me.jincrates.ecommerce.order.application.service.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderListPageServiceResponse {

    private int pageNo;
    private boolean hasNext;
    private List<OrderResponse> contents;

    @Builder
    private OrderListPageServiceResponse(int pageNo, boolean hasNext,
                                         List<OrderResponse> contents) {
        this.pageNo = pageNo;
        this.hasNext = hasNext;
        this.contents = contents;
    }
}
