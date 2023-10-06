package me.jincrates.community.comment.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.comment.application.port.CommentUseCase;
import me.jincrates.community.comment.application.service.request.CommentCreateRequest;
import me.jincrates.community.comment.application.service.request.CommentUpdateRequest;
import me.jincrates.community.comment.application.service.response.CommentResponse;
import me.jincrates.global.common.auth.JwtProvider;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "댓글 서비스", description = "댓글 API")
@RestController
@RequiredArgsConstructor
public class CommentWebAdapter {

    private final JwtProvider jwtProvider;
    private final CommentUseCase commentUseCase;

    @Operation(summary = "댓글 작성")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/api/v1/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<Long> createComment(
        @Valid @RequestBody CommentCreateRequest request,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.ok(commentUseCase.createComment(request, memberId));
    }

    @Operation(summary = "댓글 목록 조회")
    @GetMapping("/api/v1/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<List<CommentResponse>> getComments() {
        return CommonResponse.ok(commentUseCase.getComments());
    }

    @Operation(summary = "댓글 수정")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/api/v1/comments/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<Void> updateComment(
        @PathVariable("comment_id") Long commentId,
        @Valid @RequestBody CommentUpdateRequest request,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        commentUseCase.updateComment(request, memberId);

        return CommonResponse.ok(null);
    }

    @Operation(summary = "댓글 삭제")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/api/v1/comments/{comment_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse<Void> deletePost(
        @PathVariable("comment_id") Long commentId,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        commentUseCase.deleteComment(commentId, memberId);

        return CommonResponse.ok(null);
    }
}
