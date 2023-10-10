package me.jincrates.community.comment.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "댓글 response")
public record CommentResponse(
    @Schema(description = "게시글 ID")
    Long postId,

    @Schema(description = "댓글 ID")
    Long commentId,

    @Schema(description = "내용")
    String content,

    @Schema(description = "생성일시")
    LocalDateTime createdAt,

    @Schema(description = "수정일시")
    LocalDateTime updatedAt,

    @Schema(description = "작성자 ID")
    Long userId,

    @Schema(description = "작성자 이름")
    String userName
) {

}
