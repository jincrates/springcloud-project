package me.jincrates.ecommerce.member.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Schema(description = "회원 등록 request")
public record MemberCreateRequest(
        @Schema(description = "회원 이름", example = "진크라테스")
        @Length(max = 50, message = "회원 이름은 최대 50자까지만 입력할 수 있습니다.")
        @NotBlank(message = "회원 이름은 필수입니다.")
        String name,

        @Schema(description = "이메일", example = "user@email.com")
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @Schema(description = "비밀번호", example = "asdf1234")
        @NotBlank(message = "비밀번호는 필수입니다.")
        @Length(min = 8, message = "비밀번호는 8자 이상이여야합니다.")
        String password
) {

}
