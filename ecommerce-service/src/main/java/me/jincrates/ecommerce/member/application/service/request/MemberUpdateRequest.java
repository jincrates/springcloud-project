package me.jincrates.ecommerce.member.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Schema(description = "회원 수정 request")
public record MemberUpdateRequest(
        @Schema(description = "회원 이름", example = "진크라테스-수정")
        @Length(max = 50, message = "회원 이름은 최대 50자까지만 입력할 수 있습니다.")
        @NotBlank(message = "회원 이름은 필수입니다.")
        String name,

        @Schema(description = "비밀번호", example = "asdf1234")
        @Length(min = 8, message = "비밀번호는 8자 이상이여야합니다.")
        @NotBlank(message = "비밀번호는 필수입니다.")
        String password,

        @Schema(description = "한줄소개", example = "철학이란 지혜를 사랑하는 학문입니다. 저는 철학을 사랑합니다.")
        @Length(max = 100, message = "한줄소개는 최대 100자까지만 입력할 수 있습니다.")
        String bio,

        @Schema(description = "이미지 url", example = "")
        String imageUrl
) {

}
