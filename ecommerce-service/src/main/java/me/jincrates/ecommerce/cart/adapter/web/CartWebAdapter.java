package me.jincrates.ecommerce.cart.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.cart.application.port.CartUseCase;
import me.jincrates.ecommerce.cart.application.service.request.CartCreateRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductDeleteRequest;
import me.jincrates.ecommerce.cart.application.service.request.CartProductUpdateRequest;
import me.jincrates.ecommerce.cart.application.service.response.CartProductResponse;
import me.jincrates.ecommerce.cart.application.service.response.CartResponse;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "장바구니 서비스", description = "장바구니 관련 API")
@RestController
@RequiredArgsConstructor
public class CartWebAdapter {

    private final AuthPort authPort;
    private final CartUseCase cartUseCase;

    @Operation(summary = "장바구니 담기")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/api/v1/carts")
    public CommonResponse<CartResponse> createCart(
            @Valid @RequestBody CartCreateRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.created(cartUseCase.createCart(request, memberId));
    }

    @Operation(summary = "장바구니 조회")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @GetMapping("/api/v1/carts")
    public CommonResponse<CartResponse> getCart(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.ok(cartUseCase.getCart(memberId));
    }

    @Operation(summary = "장바구니 상품 수정")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/api/v1/carts")
    public CommonResponse<CartProductResponse> updateCartProduct(
            @Valid @RequestBody CartProductUpdateRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization

    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.ok(cartUseCase.updateCartProductQuantity(request, memberId));
    }

    @Operation(summary = "장바구니 상품 삭제")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/api/v1/carts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse<Void> deleteCartProduct(
            @Valid @RequestBody CartProductDeleteRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        cartUseCase.deleteCartProduct(request, memberId);
        return CommonResponse.noContent();
    }
}
