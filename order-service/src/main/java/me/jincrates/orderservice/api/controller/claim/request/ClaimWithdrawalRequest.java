package me.jincrates.orderservice.api.controller.claim.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClaimWithdrawalRequest {

    @NotNull(message = "클레임 ID는 필수입니다.")
    private Long claimId;

    @Builder
    private ClaimWithdrawalRequest(Long claimId) {
        this.claimId = claimId;
    }
}