package me.jincrates.claimservice.intg.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.jincrates.claimservice.api.controller.request.ClaimCreateRequest;
import me.jincrates.claimservice.api.controller.request.ClaimProductRequest;
import me.jincrates.claimservice.api.controller.response.ClaimResponse;
import me.jincrates.claimservice.api.service.ClaimService;
import me.jincrates.claimservice.domain.claim.ClaimReason;
import me.jincrates.claimservice.domain.claim.ClaimRepository;
import me.jincrates.claimservice.domain.claim.ClaimType;
import me.jincrates.claimservice.domain.orderproduct.OrderProduct;
import me.jincrates.claimservice.domain.orderproduct.OrderProductRepository;
import me.jincrates.claimservice.intg.IntegrationTestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ClaimServiceIntegrationTest extends IntegrationTestSupport {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @AfterEach
    void tearDown() {
        //orderRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("클레임 접수를 하면 결과가 리턴된다.")
    void createClaim() {
        // given
        Long orderId = 1L;
        OrderProduct orderProduct1 = createOrderProduct(orderId, 1L, 10);
        OrderProduct orderProduct2 = createOrderProduct(orderId, 2L, 20);
        OrderProduct orderProduct3 = createOrderProduct(orderId, 3L, 30);
        orderProductRepository.saveAll(List.of(orderProduct1, orderProduct2, orderProduct3));

        ClaimCreateRequest request = ClaimCreateRequest.builder()
            .orderId(orderId)
            .type(ClaimType.EXCHANGE)
            .reason(ClaimReason.CHANGE_MIND)
            .memo("마음이 바뀌어서 반품 접수합니다.")
            .claimProducts(List.of(
                ClaimProductRequest.builder()
                    .orderProductId(1L)
                    .quantity(5)
                    .build(),
                ClaimProductRequest.builder()
                    .orderProductId(2L)
                    .quantity(10)
                    .build(),
                ClaimProductRequest.builder()
                    .orderProductId(3L)
                    .quantity(15)
                    .build()
            ))
            .build();

        // when
        ClaimResponse result = claimService.request(request);

        // then
        assertThat(result).isNotNull();
    }

    private OrderProduct createOrderProduct(Long orderId, Long productId, int quantity) {
        return OrderProduct.builder()
            .orderId(orderId)
            .productId(productId)
            .price(quantity * 1000)
            .quantity(quantity)
            .build();
    }

    private List<ClaimProductRequest> getClaimProductRequest() {
        return List.of(
            ClaimProductRequest.builder()
                .orderProductId(1L)
                .quantity(5)
                .build(),
            ClaimProductRequest.builder()
                .orderProductId(2L)
                .quantity(10)
                .build(),
            ClaimProductRequest.builder()
                .orderProductId(3L)
                .quantity(15)
                .build()
        );
    }
}