package me.jincrates.orders.unit.domain.claim;

import me.jincrates.api.claims.domain.claim.Claim;
import me.jincrates.api.claims.domain.claim.ClaimReason;
import me.jincrates.api.claims.domain.claim.ClaimStatus;
import me.jincrates.api.claims.domain.claim.ClaimType;
import me.jincrates.api.claims.domain.orderproduct.OrderProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    @DisplayName("클레임 생성시 클레임 상태는 RECEIPT이다.")
    void receipt() {
        // given
        Long orderId = 1L;
        List<OrderProduct> orderProducts = List.of(
                createOrderProduct(orderId, 1L, 10),
                createOrderProduct(orderId, 2L, 15),
                createOrderProduct(orderId, 3L, 20)
        );

        // when
        Claim claim = Claim.create(1L, orderId, ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND,
                ClaimStatus.REQUESTED, "상세사유",
                orderProducts);

        // then
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.REQUESTED);
    }

    @Test
    @DisplayName("클레임 철회시 클레임 상태는 WITHDRAWAL이다.")
    void withdrawal() {
        // given
        Long orderId = 1L;
        List<OrderProduct> orderProducts = List.of(
                createOrderProduct(orderId, 1L, 10),
                createOrderProduct(orderId, 2L, 15),
                createOrderProduct(orderId, 3L, 20)
        );
        Claim claim = Claim.create(1L, orderId, ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND,
                ClaimStatus.REQUESTED, "상세사유",
                orderProducts);

        // when
        claim.cancel();

        // then
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.CANCELED);
    }

    @Test
    @DisplayName("클레임 승인시 클레임 상태는 APPROVAL이다.")
    void approval() {
        // given
        Long orderId = 1L;
        List<OrderProduct> orderProducts = List.of(
                createOrderProduct(orderId, 1L, 10),
                createOrderProduct(orderId, 2L, 15),
                createOrderProduct(orderId, 3L, 20)
        );
        Claim claim = Claim.create(1L, orderId, ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND,
                ClaimStatus.REQUESTED, "상세사유",
                orderProducts);

        // when
        claim.approve();

        // then
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.APPROVED);
    }

    @Test
    @DisplayName("클레임 반려시 클레임 상태는 REJECTION이다.")
    void reject() {
        // given
        Long orderId = 1L;
        List<OrderProduct> orderProducts = List.of(
                createOrderProduct(orderId, 1L, 10),
                createOrderProduct(orderId, 2L, 15),
                createOrderProduct(orderId, 3L, 20)
        );
        Claim claim = Claim.create(1L, orderId, ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND,
                ClaimStatus.REQUESTED, "상세사유",
                orderProducts);

        // when
        claim.reject("반려 사유를 입력합니다.");

        // then
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.REJECTED);
    }

    private OrderProduct createOrderProduct(Long orderId, long productId, int quantity) {
        return OrderProduct.builder()
                .orderId(orderId)
                .productId(productId)
                .quantity(quantity)
                .build();
    }
}