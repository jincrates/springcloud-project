package me.jincrates.community.tag.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.community.tag.application.port.TagUseCase;
import me.jincrates.community.tag.application.service.request.TagCreateRequest;
import me.jincrates.community.tag.application.service.response.TagResponse;
import me.jincrates.global.common.auth.JwtProvider;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "태그 서비스", description = "태그 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TagWebAdapter {

    private final JwtProvider jwtProvider;
    private final TagUseCase tagUseCase;

//    특정 해시태그로 게시글 검색: GET /tags/{tagName}/posts
//    해시태그 리스트 가져오기: GET /tags
//    게시글에 연관된 해시태그 가져오기: GET /posts/{postId}/tags
//    게시글에 해시태그 추가: POST /posts/{postId}/tags
//    게시글의 해시태그 삭제: DELETE /posts/{postId}/tags/{tagName}

    @Operation(summary = "태그 추가")
    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<TagResponse> addTag(
        @Valid @RequestBody TagCreateRequest request
        //@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        //Long memberId = jwtProvider.parseToken(authorization.substring(7));
        return CommonResponse.ok(tagUseCase.addTag(request));
    }

//    @Operation(summary = "태그 제거")
//    @DeleteMapping("/tags")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public CommonResponse<Void> removeTag(
//        @Valid @RequestBody TagDeleteRequest request,
//        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
//    ) {
//        Long memberId = jwtProvider.parseToken(authorization.substring(7));
//        tagUseCase.removeTag(request);
//        return CommonResponse.ok(null);
//    }
}
