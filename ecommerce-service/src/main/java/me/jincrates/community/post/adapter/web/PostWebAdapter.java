package me.jincrates.community.post.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.post.application.port.PostUseCase;
import me.jincrates.community.post.application.service.request.PostCreateRequest;
import me.jincrates.community.post.application.service.request.PostUpdateRequest;
import me.jincrates.community.post.application.service.response.PostResponse;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.response.CommonResponse;
import me.jincrates.global.common.response.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글 서비스", description = "게시글 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostWebAdapter {

    private final AuthPort authPort;
    private final PostUseCase postUseCase;

    @Operation(summary = "게시글 작성")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommonResponse<PostResponse> createPost(
            @Valid PostCreateRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.created(postUseCase.createPost(request, memberId));
    }

    @Operation(summary = "게시글 목록 조회")
    @GetMapping()
    public CommonResponse<PageResponse<PostResponse>> getPosts(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        List<PostResponse> contents = postUseCase.getPosts(PageRequest.of(pageNo, pageSize));
        PageResponse<PostResponse> response = PageResponse.of(pageNo, pageSize, contents);
        return CommonResponse.ok(response);
    }

    @Operation(summary = "게시글 조회")
    @GetMapping("/{postId}")
    public CommonResponse<PostResponse> getPost(
            @PathVariable Long postId
    ) {
        return CommonResponse.ok(postUseCase.getPost(postId));
    }

    @Operation(summary = "게시글 수정")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/{postId}")
    public CommonResponse<PostResponse> updatePost(
            @PathVariable Long postId,
            @Valid @RequestBody PostUpdateRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.ok(postUseCase.updatePost(request, memberId));
    }

    @Operation(summary = "게시글 삭제")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/{postId}")
    public CommonResponse<Void> deletePost(
            @PathVariable Long postId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        postUseCase.deletePost(postId, memberId);

        return CommonResponse.noContent();
    }
}
