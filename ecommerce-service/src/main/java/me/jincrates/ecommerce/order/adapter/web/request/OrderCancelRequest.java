package me.jincrates.ecommerce.order.adapter.web.request;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCancelRequest {

    private UUID orderId;

    private Long memberId;

    public OrderCancelRequest(UUID orderId) {
        this.orderId = orderId;
    }

    public void assignMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
