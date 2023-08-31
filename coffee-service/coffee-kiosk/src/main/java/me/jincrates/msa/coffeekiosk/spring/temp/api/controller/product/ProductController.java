package me.jincrates.msa.coffeekiosk.spring.temp.api.controller.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.global.common.response.CommonResponse;
import me.jincrates.msa.coffeekiosk.spring.temp.api.controller.product.request.ProductCreateRequest;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.product.ProductService;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.product.response.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/products/new")
    public CommonResponse<ProductResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request) {
        return CommonResponse.ok(productService.createProduct(request.toServiceRequest()));
    }

    @GetMapping("/api/v1/products/selling")
    public CommonResponse<List<ProductResponse>> getSellingProducts() {
        return CommonResponse.ok(productService.getSellingProducts());
    }
}
