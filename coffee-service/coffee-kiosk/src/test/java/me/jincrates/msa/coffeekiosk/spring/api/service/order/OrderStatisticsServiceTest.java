package me.jincrates.msa.coffeekiosk.spring.api.service.order;

import me.jincrates.msa.coffeekiosk.spring.IntegrationTestSupport;
import me.jincrates.msa.coffeekiosk.spring.temp.api.service.order.OrderStatisticsService;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.history.mail.MailSendHistory;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.history.mail.MailSendHistoryRepository;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.order.Order;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.order.OrderRepository;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.order.OrderStatus;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.orderprodct.OrderProductRepository;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.product.Product;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.product.ProductRepository;
import me.jincrates.msa.coffeekiosk.spring.temp.domain.product.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static me.jincrates.msa.coffeekiosk.spring.temp.domain.product.ProductSellingStatus.SELLING;
import static me.jincrates.msa.coffeekiosk.spring.temp.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class OrderStatisticsServiceTest extends IntegrationTestSupport {

    @Autowired
    private OrderStatisticsService orderStatisticsService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MailSendHistoryRepository mailSendHistoryRepository;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        mailSendHistoryRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("결제완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
    void sendOrderStatisticsMail() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 7, 28, 0, 0);

        Product product1 = createProduct("001", HANDMADE, 1000);
        Product product2 = createProduct("002", HANDMADE, 3000);
        Product product3 = createProduct("003", HANDMADE, 5000);
        List<Product> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);

        Order order1 = createPaymentCompletedOrder(LocalDateTime.of(2023, 7, 27, 23, 59, 59),
                products);
        Order order2 = createPaymentCompletedOrder(now, products);
        Order order3 = createPaymentCompletedOrder(LocalDateTime.of(2023, 7, 28, 23, 59, 59),
                products);
        Order order4 = createPaymentCompletedOrder(LocalDateTime.of(2023, 7, 29, 0, 0), products);

        // stubbing: Mock object 행위를 결정
        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        // when
        boolean result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2023, 7, 28),
                "test@email.com");

        // then
        assertThat(result).isTrue();

        List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
        assertThat(histories).hasSize(1)
                .extracting("content")
                .contains("총 매출 합계는 18000원입니다.");
    }

    private Order createPaymentCompletedOrder(LocalDateTime orderedAt, List<Product> products) {
        Order order = Order.builder()
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .products(products)
                .registeredAt(orderedAt)
                .build();

        return orderRepository.save(order);
    }

    private Product createProduct(String productNumber, ProductType type, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .price(price)
                .build();
    }
}