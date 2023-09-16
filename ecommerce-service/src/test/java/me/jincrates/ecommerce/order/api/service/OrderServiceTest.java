package me.jincrates.ecommerce.order.api.service;

import me.jincrates.ecommerce.IntegrationTestSupport;

class OrderServiceTest extends IntegrationTestSupport {

//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private MemberPort memberPort;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    @DisplayName("신규 주문을 생성합니다.")
//    void createOrder() {
//        // given
//        int quantity = 1;  // 주문수량
//
//        Member member = Member.create("홍길동", "user@email.com", passwordEncoder.encode("1234"));
//        memberRepository.save(member);
//
//        Product product = Product.create("상품명", 10000, "상품 상세설명");
//        productRepository.save(product);
//
//        Stock stock = Stock.create(product, 10);
//        stockRepository.save(stock);
//
//        OrderCreateServiceRequest request = new OrderCreateServiceRequest(product.getId(),
//                quantity);
//
//        // when
//        OrderResponse response = orderService.order(request, member.getEmail());
//
//        // then
//        assertThat(response.getId()).isNotNull();
//        assertThat(response.getOrderStatus()).isEqualTo(OrderStatus.SUCCESS);
//        assertThat(response.getOrderProducts()).hasSize(1)
//                .extracting("id", "orderPrice", "quantity")
//                .contains(
//                        tuple(1L, 10000, 1)
//                );
//
//        Stock restedStock = stockRepository.findById(stock.getId())
//                .orElseThrow(EntityNotFoundException::new);
//        assertThat(restedStock.getQuantity()).isEqualTo(9);
//    }
}