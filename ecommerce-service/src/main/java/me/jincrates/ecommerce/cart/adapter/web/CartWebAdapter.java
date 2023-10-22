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
import me.jincrates.ecommerce.cart.application.service.response.CartResponse;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "장바구니 서비스", description = "장바구니 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class CartWebAdapter {

    private final AuthPort authPort;
    private final CartUseCase cartUseCase;

    @Operation(summary = "장바구니 등록")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/api/v1/carts")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<CartResponse> createCart(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody CartCreateRequest request
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));

        return CommonResponse.ok(cartUseCase.createCart(request, memberId));
    }

    @Operation(summary = "내 장바구니 조회")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/api/v1/carts/my")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<CartResponse> getMyCart(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));

        return CommonResponse.ok(cartUseCase.getMyCart(memberId));
    }

    @Operation(summary = "장바구니 상품 수량 변경")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/api/v1/carts")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<Long> updateCartProduct(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody CartProductUpdateRequest request
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));

        return CommonResponse.ok(cartUseCase.updateCartProductQuantity(request, memberId));
    }

    @Operation(summary = "장바구니 상품 삭제")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/api/v1/carts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse<Class<Void>> deleteCartProduct(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody CartProductDeleteRequest request
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));

        cartUseCase.deleteCartProduct(request, memberId);

        return CommonResponse.ok(Void.TYPE);
    }
}
