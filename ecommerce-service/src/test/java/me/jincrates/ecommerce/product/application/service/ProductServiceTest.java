package me.jincrates.ecommerce.product.application.service;

import me.jincrates.ecommerce.IntegrationTestSupport;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
import me.jincrates.ecommerce.product.application.port.StockPort;
import me.jincrates.ecommerce.product.application.service.request.ProductCreateRequest;
import me.jincrates.ecommerce.product.application.service.response.ProductResponse;
import me.jincrates.ecommerce.product.domain.ProductSellingStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest extends IntegrationTestSupport {

    @Autowired
    private ProductUseCase productUseCase;

    @Autowired
    private ProductPort productPort;

    @Autowired
    private StockPort stockPort;

    @AfterEach
    void tearDown() {
        stockPort.deleteAllStockInBatch();
        productPort.deleteAllProductImageInBatch();
        productPort.deleteAllProductInBatch();
    }

    @Test
    @DisplayName("신규 상품을 등록한다.")
    void createProduct() {
        // given
        ProductCreateRequest request = new ProductCreateRequest("상품명", 10000, "상품 설명입니다.", 100);

        // when
        ProductResponse response = productUseCase.createProduct(request);

        // then
        assertThat(response).isNotNull()
                .extracting("productName", "price", "productDetail", "status")
                .contains("상품명", 10000, "상품 설명입니다.", ProductSellingStatus.SELLING);
    }
}