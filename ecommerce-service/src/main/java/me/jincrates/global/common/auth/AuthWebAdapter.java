package me.jincrates.global.common.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.member.application.port.MemberUseCase;
import me.jincrates.ecommerce.member.application.service.request.MemberLoginRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증 서비스", description = "토큰 발급 API")
@RestController
@RequiredArgsConstructor
public class AuthWebAdapter {
    private final JwtProvider jwtProvider;
    private final MemberUseCase memberUseCase;

    @Operation(summary = "토큰 발급")
    @PostMapping("/api/v1/login")
    public ResponseEntity<CommonResponse<MemberResponse>> login(
            @Valid @RequestBody MemberLoginRequest request
    ) {
        MemberResponse response = memberUseCase.login(request);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION,
                "Bearer " + jwtProvider.generateJwtToken(response.id()));

        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(CommonResponse.ok(response));
    }
}
