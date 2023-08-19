package me.jincrates.claimservice.unit.domain.claim;

import me.jincrates.claimservice.domain.claim.Claim;
import me.jincrates.claimservice.domain.claim.ClaimReason;
import me.jincrates.claimservice.domain.claim.ClaimStatus;
import me.jincrates.claimservice.domain.claim.ClaimType;
import me.jincrates.claimservice.domain.orderproduct.OrderProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClaimTest {

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
        Claim claim = Claim.create(orderId, ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND, "상세사유", orderProducts);

        // then
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.RECEIPT);
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
        Claim claim = Claim.create(orderId, ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND, "상세사유", orderProducts);

        // when
        claim.withdrawal();

        // then
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.WITHDRAWAL);
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
        Claim claim = Claim.create(orderId, ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND, "상세사유", orderProducts);

        // when
        claim.approval();

        // then
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.APPROVAL);
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
        Claim claim = Claim.create(orderId, ClaimType.EXCHANGE, ClaimReason.CHANGE_MIND, "상세사유", orderProducts);

        // when
        claim.reject("반려 사유를 입력합니다.");

        // then
        assertThat(claim.getStatus()).isEqualTo(ClaimStatus.REJECTION);
    }

    private OrderProduct createOrderProduct(Long orderId, long productId, int quantity) {
        return OrderProduct.builder()
                .orderId(orderId)
                .productId(productId)
                .quantity(quantity)
                .build();
    }
}