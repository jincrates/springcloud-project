package me.jincrates.ecommerce.member.application.service.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.adapter.web.response.MemberCreateResponse;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.member.domain.Role;
import me.jincrates.global.common.enumtype.Status;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateServiceResponse {

    private Long id;  // 회원 ID
    private String name;  // 회원명
    private String email;  // 이메일
    private Role role; // 권한
    private Status status;  // 상태

    public MemberCreateServiceResponse(Long id, String name, String email, Role role, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public static MemberCreateServiceResponse of(Member entity) {
        return new MemberCreateServiceResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getRole(),
                entity.getStatus()
        );
    }

    public MemberCreateResponse toResponse() {
        return MemberCreateResponse.of(this);
    }
}
