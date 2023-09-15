package me.jincrates.ecommerce.order.adapter.web.request;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.order.application.service.request.OrderCreateServiceRequest;

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

    public OrderCreateServiceRequest toServiceRequest() {
        return null;
    }
}
