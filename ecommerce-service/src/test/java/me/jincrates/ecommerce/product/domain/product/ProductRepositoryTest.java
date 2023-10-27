package me.jincrates.ecommerce.product.domain.product;

import me.jincrates.IntegrationTestSupport;

class ProductRepositoryTest extends IntegrationTestSupport {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @AfterEach
//    void tearDown() {
//        productRepository.deleteAllInBatch();
//    }
//
//    @Test
//    @DisplayName("신규 상품을 등록했을 때, 판매상태는 판매보류(HOLD)이다.")
//    void saveProduct() {
//        // given
//        Product product = Product.create("상품명", 1000, "상품 상세설명");
//
//        // when
//        Product result = productRepository.save(product);
//
//        // then
//        assertThat(result).isNotNull()
//            .extracting("productName", "price", "productDescription", "storeStatus")
//            .contains("상품명", 1000, "상품 상세설명", ProductSellingStatus.HOLD);
//    }
//
//    @Test
//    @DisplayName("상품명으로 상품을 조회할 수 있다.")
//    void findAllByProductName() {
//        // given
//        Product product1 = Product.create("상품명1", 1000, "상품 상세설명1");
//        Product product2 = Product.create("상품명2", 2000, "상품 상세설명2");
//        Product product3 = Product.create("상품명3", 3000, "상품 상세설명3");
//        Product product4 = Product.create("상품명4", 4000, "상품 상세설명4");
//        Product product5 = Product.create("상품명5", 5000, "상품 상세설명5");
//        productRepository.saveAll(List.of(product1, product2, product3, product4, product5));
//
//        // when
//        List<Product> result = productRepository.findAllByProductName("상품명3");
//
//        // then
//        assertThat(result).isNotNull()
//            .hasSize(1)
//            .extracting("productName", "price", "productDescription", "storeStatus")
//            .containsExactlyInAnyOrder(
//                tuple("상품명3", 3000, "상품 상세설명3", ProductSellingStatus.HOLD)
//            );
//    }
}