package me.jincrates.api.ecommerce.members.application.service.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.api.ecommerce.members.adapter.web.request.MemberCreateRequest;

@Getter
public class MemberCreateServiceRequest {

    private String name;  // 회원명
    private String email;  // 이메일
    private String password; // 비밀번호

    @Builder(access = AccessLevel.PRIVATE)
    private MemberCreateServiceRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static MemberCreateServiceRequest create(String name, String email, String password) {
        return MemberCreateServiceRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public static MemberCreateServiceRequest of(MemberCreateRequest request) {
        return MemberCreateServiceRequest.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
