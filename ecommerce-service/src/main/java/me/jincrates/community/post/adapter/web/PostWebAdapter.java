package me.jincrates.community.post.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.post.application.port.PostUseCase;
import me.jincrates.community.post.application.service.request.PostCreateRequest;
import me.jincrates.global.common.auth.JwtProvider;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 서비스", description = "게시글 등록/조회/삭제 API")
@RestController
@RequiredArgsConstructor
public class PostWebAdapter {

    private final JwtProvider jwtProvider;
    private final PostUseCase postUseCase;


    // 게시글 작성
    @Operation(summary = "게시글 작성")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/api/v1/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<Long> createPost(
        @Valid @RequestBody PostCreateRequest request,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));

        return CommonResponse.ok(postUseCase.createPost(request, memberId));
    }
    // 게시글 목록 조회
    // 게시글 상세 조회
    // 게시글 수정
    // 게시글 삭제
}
