package me.jincrates.claimservice.intg.api.service;

import me.jincrates.claimservice.api.controller.request.ClaimCreateRequest;
import me.jincrates.claimservice.api.controller.response.ClaimResponse;
import me.jincrates.claimservice.api.service.ClaimService;
import me.jincrates.claimservice.intg.IntegrationTestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ClaimServiceIntegrationTest extends IntegrationTestSupport {

    @Autowired
    private ClaimService claimService;

    @AfterEach
    void tearDown() {
        //orderRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("클레임 접수를 하면 결과가 리턴된다.")
    void createClaim() {
        // given
        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .build();

        // when
        ClaimResponse result = claimService.createClaim(request);

        // then
        assertThat(result).isNotNull();
    }
}