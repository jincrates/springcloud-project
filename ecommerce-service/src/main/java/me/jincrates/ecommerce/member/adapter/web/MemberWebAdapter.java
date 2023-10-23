package me.jincrates.ecommerce.member.adapter.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.member.application.port.MemberUseCase;
import me.jincrates.ecommerce.member.application.service.request.MemberCreateRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberResponse;
import me.jincrates.global.common.auth.application.AuthPort;
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
public class MemberWebAdapter {

    private final AuthPort authPort;
    private final MemberUseCase memberUseCase;

    @Operation(summary = "회원 등록")
    @PostMapping("/api/v1/members")
    public ResponseEntity<CommonResponse<MemberResponse>> createMember(
        @Valid @RequestBody MemberCreateRequest request
    ) {
        MemberResponse response = memberUseCase.register(request);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,
            "Bearer " + authPort.generateAccessToken(response.id()));

        return ResponseEntity.status(HttpStatus.CREATED)
            .headers(httpHeaders)
            .body(CommonResponse.created(response));
    }

    @Operation(summary = "내 정보 조회")
    @Parameter(name = HttpHeaders.AUTHORIZATION, hidden = true, description = "JWT Token", in = ParameterIn.HEADER, required = true)
    @GetMapping("/api/v1/members/my")
    public CommonResponse<MemberResponse> getMember(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorization
    ) {
        Long memberId = authPort.parseToken(authorization.substring(7));
        return CommonResponse.ok(memberUseCase.getMemberById(memberId));
    }

    @Operation(summary = "회원 조회")
    @GetMapping("/api/v1/members/{memberId}")
    public CommonResponse<MemberResponse> getMember(
        @PathVariable("memberId") Long memberId
    ) {
        return CommonResponse.ok(memberUseCase.getMemberById(memberId));
    }

    @Operation(summary = "전체 회원 조회")
    @GetMapping("/api/v1/members")
    public CommonResponse<List<MemberResponse>> getMembers() {
        return CommonResponse.ok(memberUseCase.getMembers());
    }
}
