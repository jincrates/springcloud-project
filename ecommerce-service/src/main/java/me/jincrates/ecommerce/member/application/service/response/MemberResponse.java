package me.jincrates.ecommerce.member.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.member.domain.Role;
import me.jincrates.global.common.enumtype.Status;

@Schema(description = "회원 response")
public record MemberResponse(
        @Schema(description = "회원 ID", example = "1")
        Long id,
        @Schema(description = "회원명", example = "진크라테스")
        String name,
        @Schema(description = "이메일", example = "user@email.com")
        String email,
        @Schema(description = "권한", example = "USER")
        Role role,
        @Schema(description = "상태", example = "ACTIVE")
        Status status
) {

    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getRole(),
                member.getStatus()
        );
    }
}
