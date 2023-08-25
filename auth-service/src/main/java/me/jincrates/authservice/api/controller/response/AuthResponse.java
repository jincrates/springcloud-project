package me.jincrates.authservice.api.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthResponse {
    private String accessToken;
    private String refreshToken;

    @Builder
    private AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
