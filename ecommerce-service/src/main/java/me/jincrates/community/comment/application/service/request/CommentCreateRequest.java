package me.jincrates.community.comment.application.service.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "댓글 작성 request")
public record CommentCreateRequest(
    @Schema(description = "댓글 내용")
    @NotBlank(message = "댓글 내용은 필수입니다.")
    String content
) {

}
