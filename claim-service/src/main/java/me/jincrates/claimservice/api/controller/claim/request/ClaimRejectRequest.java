package me.jincrates.claimservice.api.controller.claim.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClaimRejectRequest {

    @NotNull(message = "클레임 ID는 필수입니다.")
    private Long claimId;
    @NotBlank(message = "반려 사유는 필수입니다.")
    private String rejectMemo;

    @Builder
    private ClaimRejectRequest(Long claimId, String rejectMemo) {
        this.claimId = claimId;
        this.rejectMemo = rejectMemo;
    }
}
