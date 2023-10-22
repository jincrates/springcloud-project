package me.jincrates.community.post.application.service.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "게시글 response")
public record PostResponse(
        @Schema(description = "게시글 ID")
        Long postId,

        @Schema(description = "제목")
        String title,

        @Schema(description = "내용")
        String content,

        @Schema(description = "좋아요 수")
        int likeCount,

        @Schema(description = "댓글 수")
        int commentCount,

        @Schema(description = "이미지 url 목록")
        List<String> imageUrls,

        @Schema(description = "생성일시")
        LocalDateTime createdAt,

        @Schema(description = "수정일시")
        LocalDateTime updatedAt,

        @Schema(description = "작성자 ID")
        Long memberId,

        @Schema(description = "작성자 이름")
        String memberName
) {

}
