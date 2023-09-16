package me.jincrates.ecommerce.order.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "주문 취소 request")
public record OrderCancelRequest(
        @Schema(description = "주문 ID")
        @NotNull(message = "주문 ID는 필수입니다.")
        UUID orderId
) {

}
