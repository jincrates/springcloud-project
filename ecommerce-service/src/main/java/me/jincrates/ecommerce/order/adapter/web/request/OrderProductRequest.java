package me.jincrates.ecommerce.order.adapter.web.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProductRequest {

    @NotNull(message = "주문할 상품의 ID가 없습니다.")
    private Long productId;

    @NotNull(message = "주문할 상품의 개수를 입력해주세요.")
    @Min(value = 1, message = "주문할 상품의 개수를 1개 이상으로 입력해주세요.")
    private int orderQuantity;

    public OrderProductRequest(Long productId, int orderQuantity) {
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }
}
