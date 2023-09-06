package me.jincrates.api.ecommerce.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import me.jincrates.api.ecommerce.IntegrationTestSupport;
import me.jincrates.api.ecommerce.api.service.request.OrderCreateServiceRequest;
import me.jincrates.api.ecommerce.api.service.response.OrderServiceResponse;
import me.jincrates.api.ecommerce.domain.member.Member;
import me.jincrates.api.ecommerce.domain.member.MemberRepository;
import me.jincrates.api.ecommerce.domain.order.OrderRepository;
import me.jincrates.api.ecommerce.domain.product.Product;
import me.jincrates.api.ecommerce.domain.product.ProductRepository;
import me.jincrates.api.ecommerce.domain.stock.Stock;
import me.jincrates.api.ecommerce.domain.stock.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

class OrderServiceTest extends IntegrationTestSupport {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
        stockRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("신규 주문을 생성합니다.")
    void createOrder() {
        // given
        int quantity = 1;  // 주문수량

        Member member = Member.create("홍길동", "user@email.com", "1234", passwordEncoder);
        memberRepository.save(member);

        Product product = Product.create("상품명", 10000, "상품 상세설명");
        productRepository.save(product);

        Stock stock = Stock.create(product, quantity);
        stockRepository.save(stock);

        OrderCreateServiceRequest request = new OrderCreateServiceRequest(product.getId(),
            quantity);

        // when
        OrderServiceResponse response = orderService.order(request, member.getEmail());

        // then
        assertThat(response).isNotNull();
    }
}