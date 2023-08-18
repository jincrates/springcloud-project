package me.jincrates.claimservice.api.controller;

import lombok.RequiredArgsConstructor;
import me.jincrates.claimservice.api.ApiResponse;
import me.jincrates.claimservice.api.controller.request.ClaimCreateRequest;
import me.jincrates.claimservice.api.controller.request.ClaimProductRequest;
import me.jincrates.claimservice.api.controller.response.ClaimResponse;
import me.jincrates.claimservice.api.service.ClaimService;
import me.jincrates.claimservice.domain.claim.ClaimReason;
import me.jincrates.claimservice.domain.claim.ClaimType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @GetMapping("/api/v1/claims/receipt")
    public ApiResponse<ClaimResponse> createClaim(
            //@Valid @RequestBody ClaimCreateRequest
    ) {
        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.EXCHANGE)
                .reason(ClaimReason.CHANGE_MIND)
                .memo("마음이 바뀌어서 반품 접수합니다.")
                .claimProductRequests(List.of(
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

        return ApiResponse.ok(claimService.receipt(request));
    }
}
