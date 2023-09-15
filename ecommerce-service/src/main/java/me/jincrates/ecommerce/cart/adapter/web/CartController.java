package me.jincrates.ecommerce.cart.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.auth.JwtProvider;
import me.jincrates.ecommerce.cart.adapter.web.request.CartCreateRequest;
import me.jincrates.ecommerce.cart.adapter.web.response.CartResponse;
import me.jincrates.ecommerce.cart.application.port.CartUseCase;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "장바구니 서비스", description = "장바구니 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final JwtProvider jwtProvider;
    private final CartUseCase cartUseCase;

    @Operation(summary = "장바구니 등록")
    @ApiResponse(responseCode = "200", description = "장바구니 등록 성공",
            content = @Content(schema = @Schema(implementation = CartResponse.class, description = "등록된 장바구니")))
    @PostMapping("/api/v1/carts")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<?> createCart(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody CartCreateRequest request
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        request.assignMemberId(memberId);

        return CommonResponse.ok(cartUseCase.createCart(request.toServiceRequest()));
    }
}
