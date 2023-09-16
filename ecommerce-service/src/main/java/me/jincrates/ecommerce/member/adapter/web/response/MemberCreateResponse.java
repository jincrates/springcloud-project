package me.jincrates.ecommerce.member.adapter.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.application.service.response.MemberCreateServiceResponse;
import me.jincrates.ecommerce.member.domain.Role;
import me.jincrates.global.common.enumtype.Status;

@Schema(description = "회원 등록 response")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateResponse {

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

    public MemberCreateResponse(Long id, String name, String email, Role role, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public static MemberCreateResponse of(MemberCreateServiceResponse response) {
        return new MemberCreateResponse(
                response.getId(),
                response.getName(),
                response.getEmail(),
                response.getRole(),
                response.getStatus()
        );
    }
}
