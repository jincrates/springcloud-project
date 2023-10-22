package me.jincrates.global.common.auth.application;

public interface AuthPort {
    String generateAccessToken(Long memberId);

    String generateRefreshToken(Long memberId);

    Long parseToken(String jwtToken);

    String updateRefreshToken(Long memberId, String refreshToken);
}
