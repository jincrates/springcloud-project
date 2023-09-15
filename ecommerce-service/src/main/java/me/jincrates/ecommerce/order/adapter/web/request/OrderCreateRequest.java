package me.jincrates.ecommerce.order.adapter.web.request;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateRequest {

    private List<OrderProductRequest> orderProducts = new ArrayList<>();

    private Long memberId;

    public OrderCreateRequest(List<OrderProductRequest> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void assignMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
