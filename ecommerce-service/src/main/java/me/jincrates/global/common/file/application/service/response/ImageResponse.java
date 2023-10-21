package me.jincrates.global.common.file.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "파일 업로드 response")
public record ImageResponse(

        @Schema(description = "원본 이미지 url")
        String originFileUrl,

        @Schema(description = "썸네일 이미지 url")
        String thumbFileUrl
) {
}
