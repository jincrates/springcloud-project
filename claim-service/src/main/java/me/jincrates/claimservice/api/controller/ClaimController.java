package me.jincrates.claimservice.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.claimservice.api.ApiResponse;
import me.jincrates.claimservice.api.controller.request.ClaimApprovalRequest;
import me.jincrates.claimservice.api.controller.request.ClaimCreateRequest;
import me.jincrates.claimservice.api.controller.request.ClaimRejectRequest;
import me.jincrates.claimservice.api.controller.request.ClaimWithdrawalRequest;
import me.jincrates.claimservice.api.controller.response.ClaimResponse;
import me.jincrates.claimservice.api.service.ClaimService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping("/api/v1/claims/return/request")
    public ApiResponse<ClaimResponse> returnRequest(
        @Valid @RequestBody ClaimCreateRequest request
    ) {
        request.validate();

        Long userId = 1L;

        return ApiResponse.ok(claimService.request(request, userId));
    }

    @PostMapping("/api/v1/claims/exchange/request")
    public ApiResponse<ClaimResponse> exchangeRequest(
        @Valid @RequestBody ClaimCreateRequest request
    ) {
        request.validate();

        Long userId = 1L;

        return ApiResponse.ok(claimService.request(request, userId));
    }


    @PostMapping("/api/v1/claims/withdrawal")
    public ApiResponse<Long> withdrawal(
        @Valid @RequestBody ClaimWithdrawalRequest request
    ) {
        return ApiResponse.ok(claimService.cancel(request));
    }

    @PostMapping("/api/v1/claims/approval")
    public ApiResponse<Long> approval(
        @Valid @RequestBody ClaimApprovalRequest request
    ) {
        return ApiResponse.ok(claimService.approve(request));
    }

    @PostMapping("/api/v1/claims/reject")
    public ApiResponse<Long> reject(
        @Valid @RequestBody ClaimRejectRequest request
    ) {
        return ApiResponse.ok(claimService.reject(request));
    }
}
