package me.jincrates.community.tag.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "태그 생성 request")
public record TagCreateRequest(
    @Schema(description = "태그")
    @NotBlank(message = "태그는 필수입니다.")
    String tagName
) {

}
