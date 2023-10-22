package me.jincrates.community.post.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "게시글 작성 request")
public record PostCreateRequest(
        @Schema(description = "제목", example = "게시글 제목")
        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @Schema(description = "내용", example = "게시글 내용")
        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @Schema(description = "업로드 이미지 목록")
        List<String> imageUrls
) {

    public PostCreateRequest {
        imageUrls = imageUrls == null ? new ArrayList<>() : imageUrls;
    }
}
