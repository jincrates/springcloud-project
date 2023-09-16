package me.jincrates.ecommerce.member.application.service.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.adapter.web.request.MemberLoginRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberLoginServiceRequest {

    private String email;  // 이메일
    private String password; // 비밀번호

    public MemberLoginServiceRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static MemberLoginServiceRequest of(MemberLoginRequest request) {
        return new MemberLoginServiceRequest(request.getEmail(), request.getPassword());
    }
}
