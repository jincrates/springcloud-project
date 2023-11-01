package me.jincrates.global.common.file.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.enumtype.FileBucket;
import me.jincrates.global.common.file.application.port.FilePort;
import me.jincrates.global.common.file.application.service.request.FileRequest;
import me.jincrates.global.common.file.application.service.response.ImageResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "파일 서비스", description = "파일 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/{bucket}")
public class FileWebAdapter {
    private final AuthPort authPort;
    private final FilePort filePort;

    @Operation(summary = "이미지 임시 업로드")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @PostMapping(path = "/files/image/temp", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommonResponse<ImageResponse> uploadTempImage(
            @PathVariable("bucket") FileBucket bucket,
            @Valid FileRequest request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        ImageResponse response = filePort.uploadTempImage(request.file(), memberId, bucket);
        return CommonResponse.created(response);
    }

//    @Operation(summary = "이미지 파일 저장")
//    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
//    @PostMapping("/files/image")
//    @ResponseStatus(HttpStatus.CREATED)
//    public CommonResponse<List<String>> uploadFiles(
//            @RequestBody List<String> tempUrls,
//            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
//    ) {
//        Long memberId = jwtProvider.parseToken(authorization.substring(7));
//        List<String> uploadUrls = filePort.uploadImages(tempUrls, memberId, "community");
//
//        return CommonResponse.ok(uploadUrls);
//    }

//    @Operation(summary = "동영상 업로드")
//    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
//    @PostMapping(path = "/files/video/temp", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @ResponseStatus(HttpStatus.CREATED)
//    public CommonResponse<?> uploadTempVideo(
//            @Valid FileRequest request,
//            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
//    ) {
//        Long memberId = jwtProvider.parseToken(authorization.substring(7));
//        filePort.uploadTempVideo(request.file(), memberId, "community");
//
//        return CommonResponse.ok(null);
//    }
//
//    @Operation(summary = "동영상 업로드 상태 조회")
//    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
//    @GetMapping(path = "/files/video/storeStatus")
//    @ResponseStatus(HttpStatus.OK)
//    public CommonResponse<Progress> getUploadStatus(HttpSession session) {
//        Progress progress = (Progress) session.getAttribute("progress");
//        return CommonResponse.ok(progress);
//    }
}
