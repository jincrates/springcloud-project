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
import org.springframework.web.bind.annotation.*;

@Tag(name = "좋아요 서비스", description = "좋아요 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class LikeWebAdapter {

    private final JwtProvider jwtProvider;
    private final LikeUseCase likeUseCase;

    @Operation(summary = "게시글 좋아요")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/{postId}/likes")
    public CommonResponse<Boolean> likePost(
            @PathVariable(name = "postId") Long postId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        return CommonResponse.created(likeUseCase.likePost(memberId, postId));
    }

    @Operation(summary = "게시글 좋아요 취소")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/{postId}/likes")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<Boolean> cancelLikePost(
            @PathVariable(name = "postId") Long postId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        return CommonResponse.ok(likeUseCase.cancelLikePost(memberId, postId));
    }

//    @Operation(summary = "댓글 좋아요")
//    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
//    @PostMapping("/{postId}/comments/{commentId}/likes")
//    @ResponseStatus(HttpStatus.CREATED)
//    public CommonResponse<Long> likeComment(
//        @PathVariable(name = "postId") Long postId,
//        @PathVariable(name = "commentId") Long commentId,
//        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
//    ) {
//        Long memberId = jwtProvider.parseToken(authorization.substring(7));
//        return null;
//    }
//
//    @Operation(summary = "댓글 좋아요 취소")
//    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
//    @DeleteMapping("/{postId}/comments/{commentId}/likes")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public CommonResponse<Long> cancelLikeComment(
//        @PathVariable(name = "postId") Long postId,
//        @PathVariable(name = "commentId") Long commentId,
//        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
//    ) {
//        Long memberId = jwtProvider.parseToken(authorization.substring(7));
//        return null;
//    }
}
