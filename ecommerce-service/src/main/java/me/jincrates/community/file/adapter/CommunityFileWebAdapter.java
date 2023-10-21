package me.jincrates.community.file.adapter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.global.common.auth.JwtProvider;
import me.jincrates.global.common.file.application.FilePort;
import me.jincrates.global.common.file.application.FileRequest;
import me.jincrates.global.common.file.application.ImageResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "커뮤니티 파일 서비스", description = "파일 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community")
public class CommunityFileWebAdapter {
    private final JwtProvider jwtProvider;
    private final FilePort filePort;

    /*
     POST /community/files : 파일 업로드(동영상, 이미지 - 파읾명, 파일크기(단위 bytes), contentType
     GET /community/files/{fileId} : 파일 조회
     GET /community/files?postId=11 : 게시글 파일 목록 조회
     DELETE /community/files/{fileId} : 파일 삭제
     */

    @Operation(summary = "이미지 임시 저장")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping(path = "/files/image/temp", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<ImageResponse> uploadTempFile(
            @Valid FileRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        ImageResponse response = filePort.uploadTempImage(request.file(), memberId, "community");

        return CommonResponse.ok(response);
    }

    @Operation(summary = "이미지 파일 저장")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping("/files/image")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<List<String>> uploadFiles(
            @RequestBody List<String> tempUrls,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        List<String> uploadUrls = filePort.uploadImages(tempUrls, memberId, "community");

        return CommonResponse.ok(uploadUrls);
    }
}
