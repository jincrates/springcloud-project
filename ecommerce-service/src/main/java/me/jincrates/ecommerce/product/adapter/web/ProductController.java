package me.jincrates.ecommerce.product.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
import me.jincrates.ecommerce.product.application.service.request.ProductCreateRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상품 서비스", description = "상품 등록/수정/조회 API")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;

    @Operation(summary = "상품 등록")
    @ApiResponse(responseCode = "200", description = "상품 등록 성공",
            content = @Content(schema = @Schema(implementation = ProductResponse.class, description = "등록된 상품")))
    @PostMapping("/api/v1/products")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<?> createProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        return CommonResponse.ok(productUseCase.createProduct(request));
    }
}
