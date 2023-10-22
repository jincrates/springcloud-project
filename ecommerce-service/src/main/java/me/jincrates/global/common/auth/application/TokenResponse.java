package me.jincrates.global.common.auth.application;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
