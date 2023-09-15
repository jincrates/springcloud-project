package me.jincrates.api.ecommerce.orders.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

import jakarta.persistence.EntityNotFoundException;
import me.jincrates.api.ecommerce.IntegrationTestSupport;
import me.jincrates.api.ecommerce.members.adapter.persistence.MemberRepository;
import me.jincrates.api.ecommerce.members.domain.Member;
import me.jincrates.api.ecommerce.orders.api.service.request.OrderCreateServiceRequest;
import me.jincrates.api.ecommerce.orders.api.service.response.OrderServiceResponse;
import me.jincrates.api.ecommerce.orders.domain.OrderProductRepository;
import me.jincrates.api.ecommerce.orders.domain.OrderRepository;
import me.jincrates.api.ecommerce.orders.domain.OrderStatus;
import me.jincrates.api.ecommerce.products.adapter.persistence.ProductRepository;
import me.jincrates.api.ecommerce.products.adapter.persistence.StockRepository;
import me.jincrates.api.ecommerce.products.domain.Product;
import me.jincrates.api.ecommerce.products.domain.Stock;
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
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
        stockRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("신규 주문을 생성합니다.")
    void createOrder() {
        // given
        int quantity = 1;  // 주문수량

        Member member = Member.create("홍길동", "user@email.com", passwordEncoder.encode("1234"));
        memberRepository.save(member);

        Product product = Product.create("상품명", 10000, "상품 상세설명");
        productRepository.save(product);

        Stock stock = Stock.create(product, 10);
        stockRepository.save(stock);

        OrderCreateServiceRequest request = new OrderCreateServiceRequest(product.getId(),
            quantity);

        // when
        OrderServiceResponse response = orderService.order(request, member.getEmail());

        // then
        assertThat(response.getId()).isNotNull();
        assertThat(response.getOrderStatus()).isEqualTo(OrderStatus.SUCCESS);
        assertThat(response.getOrderProducts()).hasSize(1)
            .extracting("productId", "orderPrice", "quantity")
            .contains(
                tuple(1L, 10000, 1)
            );

        Stock restedStock = stockRepository.findById(stock.getId())
            .orElseThrow(EntityNotFoundException::new);
        assertThat(restedStock.getQuantity()).isEqualTo(9);
    }
}