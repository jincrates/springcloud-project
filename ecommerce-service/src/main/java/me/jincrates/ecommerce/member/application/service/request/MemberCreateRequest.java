package me.jincrates.ecommerce.member.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "회원 등록 request")
public record MemberCreateRequest(
        @Schema(description = "회원명", example = "진크라테스")
        @NotBlank(message = "회원명은 필수입니다.")
        String name,

        @Schema(description = "이메일", example = "user@email.com")
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @Schema(description = "비밀번호", example = "asdf1234")
        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 8자 이상이여야합니다.")
        String password
) {

}
