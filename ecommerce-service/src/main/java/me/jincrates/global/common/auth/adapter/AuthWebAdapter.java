package me.jincrates.global.common.auth.adapter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.member.application.port.MemberUseCase;
import me.jincrates.ecommerce.member.application.service.request.MemberLoginRequest;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.auth.application.TokenResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증 서비스", description = "토큰 발급 API")
@RestController
@RequiredArgsConstructor
public class AuthWebAdapter {
    private final AuthPort authPort;
    private final MemberUseCase memberUseCase;

    @Operation(summary = "토큰 발급", description = "accessToken, refreshToken 발급")
    @PostMapping("/api/v1/login")
    public CommonResponse<TokenResponse> login(
            @Valid @RequestBody MemberLoginRequest request
    ) {
        Long memberId = memberUseCase.login(request).id();
        TokenResponse response = new TokenResponse(
                authPort.generateAccessToken(memberId),
                authPort.generateRefreshToken(memberId)
        );

        return CommonResponse.created(response);
    }

    @Operation(summary = "Access 토큰 발급", description = "이때 사용된 Refresh 토큰도 새로 갱신된다.(기존 Refresh 토큰 재사용X)")
    @PostMapping("/api/v1/token")
    public CommonResponse<TokenResponse> generateAccessToken(
            String refreshToken
    ) {
        Long memberId = authPort.parseToken(refreshToken);
        TokenResponse response = new TokenResponse(
                authPort.generateAccessToken(memberId),
                authPort.updateRefreshToken(memberId, refreshToken)
        );

        return CommonResponse.created(response);
    }
}
