package me.jincrates.ecommerce.product.application.service;

import me.jincrates.IntegrationTestSupport;
import me.jincrates.ecommerce.product.application.port.ProductPort;
import me.jincrates.ecommerce.product.application.port.ProductUseCase;
import me.jincrates.ecommerce.product.application.port.StockPort;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

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

//    @Test
//    @DisplayName("신규 상품을 등록한다.")
//    void createProduct() {
//        // given
//        ProductCreateRequest request = new ProductCreateRequest("상품명", 10000, "상품 설명입니다.", 100);
//
//        // when
//        ProductResponse response = productUseCase.createProduct(request);
//
//        // then
//        assertThat(response).isNotNull()
//                .extracting("productName", "price", "productDetail", "storeStatus")
//                .contains("상품명", 10000, "상품 설명입니다.", ProductSellingStatus.SELLING);
//    }
}