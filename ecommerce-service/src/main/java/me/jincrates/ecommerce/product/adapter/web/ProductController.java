package me.jincrates.ecommerce.product.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.adapter.web.request.ProductCreateRequest;
import me.jincrates.ecommerce.product.adapter.web.response.ProductResponse;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
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
    public CommonResponse<?> createBoiler(
        @Valid @RequestBody ProductCreateRequest request
    ) {
        return CommonResponse.ok(
            productUseCase.createProduct(request.toServiceRequest()).toResponse());
    }
//
//    @Operation(summary = "보일러 조회")
//    @Parameter(name = "boilerId", in = ParameterIn.PATH, required = true, example = "1")
//    @ApiResponse(responseCode = "200", description = "보일러 조회 성공",
//            content = @Content(schema = @Schema(implementation = BoilerCreateResponse.class, description = "등록된 boiler")))
//    @GetMapping("/api/v1/boiler/{boilerId}")
//    public CommonResponse<BoilerReadResponse> getBoiler(@PathVariable("boilerId") Long boilerId) {
//        return CommonResponse.ok(service.findById(boilerId));
//    }
}
