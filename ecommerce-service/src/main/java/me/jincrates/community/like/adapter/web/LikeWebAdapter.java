package me.jincrates.community.like.adapter.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.like.application.port.LikeUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "좋아요 서비스", description = "좋아요 API")
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor

public class LikeWebAdapter {

    private final LikeUseCase likeUseCase;

//    게시글 좋아요: POST /posts/{postId}/likes
//    게시글 좋아요 취소: DELETE /posts/{postId}/likes
//    댓글 좋아요: POST /posts/{postId}/comments/{commentId}/likes
//    댓글 좋아요 취소: DELETE /posts/{postId}/comments/{commentId}/likes
}
