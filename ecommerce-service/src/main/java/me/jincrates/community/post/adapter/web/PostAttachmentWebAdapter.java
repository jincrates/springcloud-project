package me.jincrates.community.post.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.post.application.port.PostUseCase;
import me.jincrates.community.post.application.service.request.PostCreateRequest;
import me.jincrates.community.post.application.service.response.PostResponse;
import me.jincrates.global.common.auth.JwtProvider;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 첨부파일 서비스", description = "게시글 첨부파일  API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostAttachmentWebAdapter {

    private final JwtProvider jwtProvider;
    private final PostUseCase postUseCase;

    @Operation(summary = "게시글 첨부 파일 추가")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/{postId}/attachments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<PostResponse> createPostAttachment(
        @Valid @RequestBody PostCreateRequest request,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.ok(postUseCase.createPost(request, memberId));
    }

    @Operation(summary = "게시글 첨부 파일 목록 조회")
    @GetMapping("/{postId}/attachments")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<List<PostResponse>> getPostAttachments() {
        return CommonResponse.ok(postUseCase.getPosts());
    }

    @Operation(summary = "게시글 첨부 파일 조회")
    @GetMapping("/{postId}/attachments/{attachmentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<PostResponse> getPostAttachment(
        @PathVariable Long postId,
        @PathVariable Long attachmentId
    ) {
        return CommonResponse.ok(postUseCase.getPost(postId));
    }

    @Operation(summary = "게시글 첨부 파일 삭제")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/{postId}/attachments/{attachmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse<Void> deletePostAttachment(
        @PathVariable Long postId,
        @PathVariable Long attachmentId,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        postUseCase.deletePost(postId, memberId);

        return CommonResponse.ok(null);
    }
}
