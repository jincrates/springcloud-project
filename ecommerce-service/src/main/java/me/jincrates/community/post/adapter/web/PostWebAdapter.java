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
import me.jincrates.community.post.application.service.request.PostUpdateRequest;
import me.jincrates.community.post.application.service.response.PostResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 서비스", description = "게시글 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostWebAdapter {

    private final JwtProvider jwtProvider;
    private final PostUseCase postUseCase;

    @Operation(summary = "게시글 작성")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<PostResponse> createPost(
        @Valid @RequestBody PostCreateRequest request,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.ok(postUseCase.createPost(request, memberId));
    }

    @Operation(summary = "게시글 목록 조회")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<List<PostResponse>> getPosts() {
        //TODO: 페이징 처리
        return CommonResponse.ok(postUseCase.getPosts());
    }

    @Operation(summary = "게시글 조회")
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<PostResponse> getPost(
        @PathVariable Long postId
    ) {
        return CommonResponse.ok(postUseCase.getPost(postId));
    }

    @Operation(summary = "게시글 수정")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<PostResponse> updatePost(
        @PathVariable Long postId,
        @Valid @RequestBody PostUpdateRequest request,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.ok(postUseCase.updatePost(request, memberId));
    }

    @Operation(summary = "게시글 삭제")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse<Void> deletePost(
        @PathVariable Long postId,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        postUseCase.deletePost(postId, memberId);

        return CommonResponse.ok(null);
    }
}
