package me.jincrates.ecommerce.cart.adapter.web;

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
import me.jincrates.ecommerce.cart.application.port.CartUseCase;
import me.jincrates.ecommerce.cart.application.service.request.CartCreateRequest;
import me.jincrates.ecommerce.cart.application.service.response.CartResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "장바구니 서비스", description = "장바구니 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class CartWebAdapter {

    private final JwtProvider jwtProvider;
    private final CartUseCase cartUseCase;

    @Operation(summary = "장바구니 등록")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @ApiResponse(responseCode = "200", description = "장바구니 등록 성공",
        content = @Content(schema = @Schema(implementation = CartResponse.class, description = "등록된 장바구니")))
    @PostMapping("/api/v1/carts")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<?> createCart(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
        @Valid @RequestBody CartCreateRequest request
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.ok(cartUseCase.createCart(request, memberId));
    }
}
