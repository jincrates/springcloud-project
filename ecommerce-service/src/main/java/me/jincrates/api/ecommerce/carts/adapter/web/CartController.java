package me.jincrates.api.ecommerce.carts.adapter.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.carts.adapter.web.usecase.CartUseCase;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "장바구니 서비스", description = "장바구니 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartUseCase cartUseCase;
}
