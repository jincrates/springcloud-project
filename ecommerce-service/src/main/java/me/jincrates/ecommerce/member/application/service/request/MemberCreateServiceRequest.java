package me.jincrates.ecommerce.member.application.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.adapter.web.request.MemberCreateRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateServiceRequest {

    private String name;  // 회원명
    private String email;  // 이메일
    private String password; // 비밀번호

    public MemberCreateServiceRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static MemberCreateServiceRequest of(MemberCreateRequest request) {
        return new MemberCreateServiceRequest(request.getName(), request.getEmail(), request.getPassword());
    }
}
