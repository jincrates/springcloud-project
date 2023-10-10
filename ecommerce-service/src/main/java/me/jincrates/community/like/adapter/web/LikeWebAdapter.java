package me.jincrates.community.like.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.like.application.port.LikeUseCase;
import me.jincrates.global.common.auth.JwtProvider;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "좋아요 서비스", description = "좋아요 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class LikeWebAdapter {

    private final JwtProvider jwtProvider;
    private final LikeUseCase likeUseCase;

    @Operation(summary = "게시글 좋아요")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/{post_id}/likes")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<Long> likePost(
        @PathVariable("post_id") Long postId,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        return null;
    }

    @Operation(summary = "게시글 좋아요 취소")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/{post_id}/likes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse<Long> cancelLikePost(
        @PathVariable("post_id") Long postId,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        return null;
    }

    @Operation(summary = "댓글 좋아요")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/{post_id}/comments/{comment_id}/likes")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<Long> likeComment(
        @PathVariable("post_id") Long postId,
        @PathVariable("comment_id") Long commendId,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        return null;
    }

    @Operation(summary = "댓글 좋아요 취소")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/{post_id}/comments/{comment_id}/likes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse<Long> cancelLikeComment(
        @PathVariable("post_id") Long postId,
        @PathVariable("comment_id") Long commendId,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        return null;
    }
}
