package com.example.claim.domain.claim;

import static com.example.claim.domain.claim.ClaimReason.CHANGE_MIND;
import static com.example.claim.domain.claim.ClaimReason.ORDERING_MISTAKE;
import static com.example.claim.domain.claim.ClaimReason.PRODUCT_DEFECTS;
import static com.example.claim.domain.claim.ClaimStatus.RECEIPT;
import static com.example.claim.domain.claim.ClaimType.EXCHANGE;
import static com.example.claim.domain.claim.ClaimType.RETURN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class ClaimRepositoryTest {

    @Autowired
    private ClaimRepository claimRepository;

    @AfterEach
    void tearDown() {
        claimRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("저장한 클레임의 상태는 접수(RECEIPT)이다.")
    void createClaim() {
        // given
        Claim claim = Claim.create(1L, RETURN, CHANGE_MIND, "마음이 바뀌었어요.",
            null, null);

        claimRepository.save(claim);

        // when
        List<Claim> claims = claimRepository.findAll();

        // then
        assertThat(claims).hasSize(1)
            .extracting("orderId", "type", "status", "reason", "memo")
            .contains(
                tuple(1L, RETURN, RECEIPT, CHANGE_MIND, "마음이 바뀌었어요.")
            );
    }

    @Test
    @DisplayName("저장된 클레임 리스트를 조회한다.")
    void findAll() {
        // given
        Claim claim1 = Claim.create(1L, RETURN, CHANGE_MIND, "마음이 바뀌었어요.",
            null, null);
        Claim claim2 = Claim.create(2L, RETURN, PRODUCT_DEFECTS, "상품이 박살났어요.",
            null, null);
        Claim claim3 = Claim.create(3L, EXCHANGE, ORDERING_MISTAKE,
            "사이즈를 잘못 시켰어요.", null, null);

        claimRepository.saveAll(List.of(claim1, claim2, claim3));

        // when
        List<Claim> claims = claimRepository.findAll();

        // then
        assertThat(claims).hasSize(3)
            .extracting("orderId", "type", "reason", "memo")
            .containsExactlyInAnyOrder(
                tuple(1L, RETURN, CHANGE_MIND, "마음이 바뀌었어요."),
                tuple(2L, RETURN, PRODUCT_DEFECTS, "상품이 박살났어요."),
                tuple(3L, EXCHANGE, ORDERING_MISTAKE, "사이즈를 잘못 시켰어요.")
            );
    }
}