package me.jincrates.ecommerce.order.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.order.application.port.OrderUseCase;
import me.jincrates.ecommerce.order.application.service.request.OrderCreateRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderResponse;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.response.CommonResponse;
import me.jincrates.global.common.response.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "주문 서비스", description = "주문 API")
@RestController
@RequiredArgsConstructor
public class OrderWebAdapter {

    //TODO: UseCase 합치기
    private final AuthPort authPort;
    private final OrderUseCase orderUseCase;

    @Operation(summary = "주문 생성")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/api/v1/orders")
    public CommonResponse<OrderResponse> createOrder(
            @Valid @RequestBody OrderCreateRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.created(orderUseCase.createOrder(request, memberId));
    }

    @Operation(summary = "주문 취소")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/api/v1/orders/{orderId}/cancel")
    public CommonResponse<OrderResponse> cancelOrder(
            @PathVariable("orderId") Long orderId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.ok(orderUseCase.cancelOrder(orderId, memberId));
    }

    @Operation(summary = "주문 목록 조회")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @GetMapping("/api/v1/orders")
    public CommonResponse<PageResponse<OrderResponse>> getOrders(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        List<OrderResponse> contents = orderUseCase.getOrders(memberId, PageRequest.of(pageNo, pageSize));
        PageResponse<OrderResponse> response = PageResponse.of(pageNo, pageSize, contents);
        return CommonResponse.ok(response);
    }

    @Operation(summary = "주문 조회")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @GetMapping("/api/v1/orders/{orderId}")
    public CommonResponse<OrderResponse> getOrder(
            @PathVariable("orderId") Long orderId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        OrderResponse response = orderUseCase.getOrder(orderId, memberId);
        return CommonResponse.ok(response);
    }
}
