package me.jincrates.msa.coffeekiosk.spring.api.controller.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.api.service.product.ProductService;
import me.jincrates.msa.coffeekiosk.spring.api.service.product.response.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProducts();
    }
}
