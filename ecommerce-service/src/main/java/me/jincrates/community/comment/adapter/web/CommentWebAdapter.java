package me.jincrates.community.comment.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.comment.application.port.CommentUseCase;
import me.jincrates.community.comment.application.service.request.CommentCreateRequest;
import me.jincrates.community.comment.application.service.request.CommentUpdateRequest;
import me.jincrates.community.comment.application.service.response.CommentResponse;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글 서비스", description = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class CommentWebAdapter {

    private final AuthPort authPort;
    private final CommentUseCase commentUseCase;

    @Operation(summary = "댓글 작성")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/{postId}/comments")
    public CommonResponse<CommentResponse> createComment(
            @PathVariable(name = "postId") Long postId,
            @Valid @RequestBody CommentCreateRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.created(commentUseCase.createComment(request, memberId, postId));
    }

    @Operation(summary = "댓글 목록 조회")
    @GetMapping("/{postId}/comments")
    public CommonResponse<List<CommentResponse>> getComments(
            @PathVariable(name = "postId") Long postId
    ) {
        return CommonResponse.ok(commentUseCase.getComments(postId));
    }

    @Operation(summary = "댓글 수정")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/{postId}/comments/{commentId}")
    public CommonResponse<CommentResponse> updateComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId,
            @Valid @RequestBody CommentUpdateRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));

        return CommonResponse.ok(
                commentUseCase.updateComment(request, memberId, postId, commentId));
    }

    @Operation(summary = "댓글 삭제")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/{postId}/comments/{commentId}")
    public CommonResponse<Void> deletePost(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        commentUseCase.deleteComment(memberId, postId, commentId);

        return CommonResponse.noContent();
    }
}
