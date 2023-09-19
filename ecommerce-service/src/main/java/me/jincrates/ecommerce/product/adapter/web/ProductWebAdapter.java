package me.jincrates.ecommerce.product.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
import me.jincrates.ecommerce.product.application.service.request.ProductCreateRequest;
import me.jincrates.ecommerce.product.application.service.request.ProductSearchRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;
import me.jincrates.global.common.response.CommonResponse;
import me.jincrates.global.common.response.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상품 서비스", description = "상품 등록/수정/조회 API")
@RestController
@RequiredArgsConstructor
public class ProductWebAdapter {

    private final ProductUseCase productUseCase;

    @Operation(summary = "상품 등록")
    @PostMapping("/api/v1/products")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<ProductResponse> createProduct(
        @Valid @RequestBody ProductCreateRequest request
    ) {
        return CommonResponse.ok(productUseCase.createProduct(request));
    }

    @Operation(summary = "전체 상품 조회")
    @GetMapping("/api/v1/products")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<PageResponse<ProductResponse>> getAllProduct(
        ProductSearchRequest request,
        @RequestParam(name = "page_no", defaultValue = "0", required = false) int pageNo,
        @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize

    ) {
        List<ProductResponse> products = productUseCase.getAllProduct(request,
            PageRequest.of(pageNo, pageSize));

        PageResponse response = PageResponse.create(pageNo, pageSize, products);

        return CommonResponse.ok(response);
    }


    @Operation(summary = "상품 조회")
    @GetMapping("/api/v1/products/{product_id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<ProductResponse> getProduct(
        @PathVariable("product_id") Long productId
    ) {
        return CommonResponse.ok(productUseCase.getProduct(productId));
    }
}
