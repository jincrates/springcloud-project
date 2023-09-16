package me.jincrates.ecommerce.order.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "주문 생성 request")
public record OrderCreateRequest(
        @Schema(description = "주문 상품 목록")
        @NotEmpty(message = "주문할 상품을 추가해 주세요.")
        @Size(min = 1, message = "주문할 상품을 추가해 주세요.")
        List<OrderProductRequest> orderProducts
) {

    public OrderCreateRequest {
        orderProducts = orderProducts == null ? new ArrayList<>() : orderProducts;
    }
}
