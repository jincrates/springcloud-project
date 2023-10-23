package me.jincrates.ecommerce.order.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.order.application.port.OrderCancelUseCase;
import me.jincrates.ecommerce.order.application.port.OrderCreateUseCase;
import me.jincrates.ecommerce.order.application.service.request.OrderCancelRequest;
import me.jincrates.ecommerce.order.application.service.request.OrderCreateRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderResponse;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주문 서비스", description = "주문 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class OrderWebAdapter {

    private final AuthPort authPort;
    private final OrderCreateUseCase orderCreateUseCase;
    private final OrderCancelUseCase orderCancelUseCase;

    @Operation(summary = "주문 등록")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/api/v1/orders")
    public CommonResponse<OrderResponse> createOrder(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
        @Valid @RequestBody OrderCreateRequest request
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));

        return CommonResponse.created(orderCreateUseCase.createOrder(request, memberId));
    }

    @Operation(summary = "주문 취소")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/api/v1/orders")
    public CommonResponse<OrderResponse> cancelOrder(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
        @Valid @RequestBody OrderCancelRequest request
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));

        return CommonResponse.ok(orderCancelUseCase.cancelOrder(request, memberId));
    }
}
