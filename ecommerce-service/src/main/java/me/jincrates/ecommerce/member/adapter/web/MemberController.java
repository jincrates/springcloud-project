package me.jincrates.ecommerce.member.adapter.web;

import static java.util.stream.Collectors.toList;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.auth.JwtProvider;
import me.jincrates.ecommerce.member.adapter.web.request.MemberCreateRequest;
import me.jincrates.ecommerce.member.adapter.web.response.MemberCreateResponse;
import me.jincrates.ecommerce.member.adapter.web.response.MemberResponse;
import me.jincrates.ecommerce.member.application.port.MemberUseCase;
import me.jincrates.ecommerce.member.application.service.response.MemberServiceResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 서비스", description = "회원 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;
    private final JwtProvider jwtProvider;

    @Operation(summary = "회원 등록")
    @ApiResponse(responseCode = "200", description = "회원 등록 성공",
        content = @Content(schema = @Schema(implementation = MemberCreateResponse.class, description = "회원 정보")))
    @PostMapping("/api/v1/members")
    public ResponseEntity<?> createMember(
        @Valid @RequestBody MemberCreateRequest request) {
        MemberCreateResponse response = memberUseCase.register(request.toServiceRequest())
            .toResponse();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,
            "Bearer " + jwtProvider.generateJwtToken(response.getId()));

        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(httpHeaders)
            .body(CommonResponse.ok(response));
    }

    @Operation(summary = "내 정보 조회")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @ApiResponse(responseCode = "200", description = "내 정보 조회 성공",
        content = @Content(schema = @Schema(implementation = MemberResponse.class, description = "회원 정보")))
    @GetMapping("/api/v1/members/my")
    public CommonResponse<?> getMember(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        return CommonResponse.ok(memberUseCase.getMemberById(memberId).toResponse());
    }

    @Operation(summary = "회원 조회")
    @ApiResponse(responseCode = "200", description = "회원 조회 성공",
        content = @Content(schema = @Schema(implementation = MemberResponse.class, description = "회원 정보")))
    @GetMapping("/api/v1/members/{member_id}")
    public CommonResponse<?> getMember(
        @PathVariable("member_id") Long memberId
    ) {
        return CommonResponse.ok(memberUseCase.getMemberById(memberId).toResponse());
    }

    @Operation(summary = "전체 회원 조회")
    @ApiResponse(responseCode = "200", description = "전체 회원 조회 성공",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = MemberResponse.class, description = "회원 리스트"))))
    @GetMapping("/api/v1/members")
    public CommonResponse<?> getMembers() {
        return CommonResponse.ok(memberUseCase.getMembers().stream()
            .map(MemberServiceResponse::toResponse)
            .collect(toList()));
    }
}
