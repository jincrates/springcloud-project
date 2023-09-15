package me.jincrates.ecommerce.member.application.service.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.ecommerce.member.adapter.web.response.MemberResponse;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.member.domain.Role;
import me.jincrates.global.common.enumtype.Status;

@Getter
public class MemberServiceResponse {

    private Long id;  // 회원 ID
    private String name;  // 회원명
    private String email;  // 이메일
    private Role role; // 권한
    private Status status;  // 상태

    @Builder(access = AccessLevel.PRIVATE)
    private MemberServiceResponse(Long id, String name, String email, Role role, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public static MemberServiceResponse of(Member member) {
        return MemberServiceResponse.builder()
            .id(member.getId())
            .name(member.getName())
            .email(member.getEmail())
            .role(member.getRole())
            .status(member.getStatus())
            .build();
    }

    public MemberResponse toResponse() {
        return MemberResponse.of(this);
    }
}
