package me.jincrates.ecommerce.order.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.auth.JwtProvider;
import me.jincrates.ecommerce.order.application.port.OrderCancelUseCase;
import me.jincrates.ecommerce.order.application.port.OrderCreateUseCase;
import me.jincrates.ecommerce.order.application.service.request.OrderCancelRequest;
import me.jincrates.ecommerce.order.application.service.request.OrderCreateRequest;
import me.jincrates.ecommerce.order.application.service.response.OrderResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "주문 서비스", description = "주문 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final JwtProvider jwtProvider;
    private final OrderCreateUseCase orderCreateUseCase;
    private final OrderCancelUseCase orderCancelUseCase;

    @Operation(summary = "주문 등록")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @ApiResponse(responseCode = "200", description = "주문 등록 성공",
            content = @Content(schema = @Schema(implementation = OrderResponse.class, description = "등록된 주문")))
    @PostMapping("/api/v1/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<?> createOrder(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody OrderCreateRequest request
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.ok(orderCreateUseCase.createOrder(request, memberId));
    }

    @Operation(summary = "주문 취소")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @ApiResponse(responseCode = "200", description = "주문 취소 성공",
            content = @Content(schema = @Schema(implementation = OrderResponse.class, description = "등록된 주문")))
    @PatchMapping("/api/v1/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<?> cancelOrder(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody OrderCancelRequest request
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.ok(orderCancelUseCase.cancelOrder(request, memberId));
    }
}
