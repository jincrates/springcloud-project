package me.jincrates.msa.coffeekiosk.spring.api.service.product;

import static me.jincrates.msa.coffeekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static me.jincrates.msa.coffeekiosk.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;

import me.jincrates.msa.coffeekiosk.spring.api.controller.product.request.ProductCreateRequest;
import me.jincrates.msa.coffeekiosk.spring.api.service.product.response.ProductResponse;
import me.jincrates.msa.coffeekiosk.spring.domain.product.Product;
import me.jincrates.msa.coffeekiosk.spring.domain.product.ProductRepository;
import me.jincrates.msa.coffeekiosk.spring.domain.product.ProductSellingStatus;
import me.jincrates.msa.coffeekiosk.spring.domain.product.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("신규 상품을 등록했을 때, 상품번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다. ")
    void createProduct() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.save(product1);

        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingStatus(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        // when
        ProductResponse productResponse = productService.createProduct(request);

        // then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("002", HANDMADE, SELLING, "카푸치노", 5000);
    }

    private Product createProduct(String productNumber, ProductType type,
        ProductSellingStatus productSellingStatus, String productName, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(type)
            .sellingStatus(productSellingStatus)
            .name(productName)
            .price(price)
            .build();
    }
}