package me.jincrates.community.tag.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "태그 생성 request")
public record TagResponse(
    @Schema(description = "태그 ID")
    Long tagId,

    @Schema(description = "태그")
    String tagName
) {

}
