package me.jincrates.api.ecommerce.api.service.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.api.ecommerce.domain.Status;
import me.jincrates.api.ecommerce.domain.member.Member;
import me.jincrates.api.ecommerce.domain.member.Role;

@Getter
public class MemberCreateServiceResponse {

    private Long id;  // 회원 ID
    private String name;  // 회원명
    private String email;  // 이메일
    private Role role; // 권한
    private Status status;  // 상태

    @Builder(access = AccessLevel.PRIVATE)
    private MemberCreateServiceResponse(Long id, String name, String email, Role role, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public static MemberCreateServiceResponse of(Member member) {
        return MemberCreateServiceResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .role(member.getRole())
                .status(member.getStatus())
                .build();
    }
}
