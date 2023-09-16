package me.jincrates.ecommerce.member.adapter.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.ecommerce.member.application.service.request.MemberCreateServiceRequest;

@Schema(description = "회원 등록 request")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateRequest {

    @Schema(description = "회원명", example = "진크라테스")
    @NotBlank(message = "회원명은 필수입니다.")
    private String name;  // 회원명

    @Schema(description = "이메일", example = "user@email.com")
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;  // 이메일

    @Schema(description = "비밀번호", example = "asdf1234")
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 8자 이상이여야합니다.")
    private String password; // 비밀번호

    public MemberCreateRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public MemberCreateServiceRequest toServiceRequest() {
        return MemberCreateServiceRequest.of(this);
    }
}
