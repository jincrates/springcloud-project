package me.jincrates.authservice.api.controller;

import lombok.RequiredArgsConstructor;
import me.jincrates.authservice.api.controller.request.AuthRequest;
import me.jincrates.authservice.api.controller.response.AuthResponse;
import me.jincrates.authservice.config.jwt.TokenProvider;
import me.jincrates.authservice.domain.AuthUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/api/v1/auth")
    private ApiResponse<AuthResponse> createAuthToken(@RequestBody AuthRequest request) throws Exception {

        try {
            AuthUser user = AuthUser.builder()
                    .username(request.getUsername())
                    .build();

            String accessToken = tokenProvider.generateToken(user);
            String refreshToken = tokenProvider.generateRefreshToken(user);

            return ApiResponse.ok(AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build());
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password", e);
        }
    }

    @PostMapping("/auth/token")
    private void refresh() {

        //{
        //  "accessToken": "asdiofjzxl;ckvjoiasjewr.asdfoiasjdflkajsdf.asdfivjiaosdjf",
        //}
    }

}
