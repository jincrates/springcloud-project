package me.jincrates.api.ecommerce.members.api.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.api.ecommerce.members.api.service.response.MemberServiceResponse;
import me.jincrates.api.ecommerce.members.domain.Role;
import me.jincrates.api.global.common.enumtype.Status;

@Schema(description = "회원 response")
@Getter
public class MemberResponse {

    @Schema(description = "회원 ID", example = "1")
    private Long id;

    @Schema(description = "회원명", example = "진크라테스")
    private String name;

    @Schema(description = "이메일", example = "user@email.com")
    private String email;

    @Schema(description = "권한", example = "USER")
    private Role role;

    @Schema(description = "상태", example = "ACTIVE")
    private Status status;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberResponse(Long id, String name, String email, Role role, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public static MemberResponse of(MemberServiceResponse response) {
        return MemberResponse.builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .role(response.getRole())
                .status(response.getStatus())
                .build();
    }
}
