package me.jincrates.ecommerce.member.adapter.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.application.service.request.MemberLoginServiceRequest;

@Schema(description = "회원 로그인 request")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberLoginRequest {
    @Schema(description = "이메일", example = "user@email.com")
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;  // 이메일

    @Schema(description = "비밀번호", example = "asdf1234")
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 8자 이상이여야합니다.")
    private String password; // 비밀번호

    public MemberLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MemberLoginServiceRequest toServiceRequest() {
        return MemberLoginServiceRequest.of(this);
    }
}
