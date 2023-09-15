package me.jincrates.ecommerce.order.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.auth.JwtProvider;
import me.jincrates.ecommerce.order.adapter.web.request.OrderCreateRequest;
import me.jincrates.ecommerce.order.adapter.web.response.OrderResponse;
import me.jincrates.ecommerce.order.application.port.OrderUseCase;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주문 서비스", description = "주문 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final JwtProvider jwtProvider;
    private final OrderUseCase orderUseCase;

    @Operation(summary = "주문 등록")
    @ApiResponse(responseCode = "200", description = "주문 등록 성공",
        content = @Content(schema = @Schema(implementation = OrderResponse.class, description = "등록된 주문")))
    @PostMapping("/api/v1/products")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<?> createBoiler(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
        @Valid @RequestBody OrderCreateRequest request
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        request.assignMemberId(memberId);

        return CommonResponse.ok(
            orderUseCase.createOrder(request.toServiceRequest()));
    }
}
