package me.jincrates.msa.coffeekiosk.spring.api.service.product;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.api.controller.product.request.ProductCreateRequest;
import me.jincrates.msa.coffeekiosk.spring.api.service.product.response.ProductResponse;
import me.jincrates.msa.coffeekiosk.spring.domain.product.Product;
import me.jincrates.msa.coffeekiosk.spring.domain.product.ProductRepository;
import me.jincrates.msa.coffeekiosk.spring.domain.product.ProductSellingStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductCreateRequest request) {
        String nextProductNumber = createNextProductNumber();

        return ProductResponse.builder()
            .productNumber(nextProductNumber)
            .type(request.getType())
            .sellingStatus(request.getSellingStatus())
            .name(request.getName())
            .price(request.getPrice())
            .build();
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(
            ProductSellingStatus.forDisplay());

        return products.stream()
            .map(ProductResponse::of)
            .collect(Collectors.toList());
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        // 9 -> 009
        return String.format("%03d", nextProductNumberInt);
    }
}
