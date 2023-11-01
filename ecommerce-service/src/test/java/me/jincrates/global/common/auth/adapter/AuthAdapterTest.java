package me.jincrates.global.common.auth.adapter;

import me.jincrates.IntegrationTestSupport;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.auth.domain.MemberAuth;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AuthAdapterTest extends IntegrationTestSupport {
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private MemberAuthRepository memberAuthRepository;

    @Autowired
    private AuthPort authPort;

    @AfterEach
    void tearDown() {
        memberAuthRepository.deleteAllInBatch();
    }

    @DisplayName("회원 ID를 가지고 AccessToken을 발급합니다.")
    @Test
    void generateAccessToken() {
        // given
        Long memberId = 1L;

        // when
        String accessToken = authPort.generateAccessToken(memberId);

        // then
        assertThat(jwtProvider.parseToken(accessToken)).isEqualTo(memberId);
    }

    @DisplayName("회원 ID를 가지고 RefreshToken을 발급한다.")
    @Test
    void generateRefreshToken() {
        // given
        Long memberId = 1L;

        // when
        String refreshToken = authPort.generateRefreshToken(memberId);

        // then
        assertAll(
                () -> assertThat(jwtProvider.parseToken(refreshToken)).isEqualTo(memberId)
        );
    }

    @DisplayName("이미 RefreshToken을 발급했다면, 토큰이 만료되기 전까지 발급된 토큰 정보를 반환한다.")
    @Test
    void generateRefreshToken2() {
        // given
        Long memberId = 1L;
        String refreshToken = authPort.generateRefreshToken(memberId);

        // when
        String retryRefreshToken = authPort.generateRefreshToken(memberId);

        // then
        assertAll(
                () -> assertThat(jwtProvider.parseToken(refreshToken)).isEqualTo(memberId),
                () -> assertThat(retryRefreshToken).isEqualTo(refreshToken)
        );
    }

    @DisplayName("JWT 토큰을 복호화하면 회원 ID가 반환된다.")
    @Test
    void parseToken() {
        // given
        Long memberId = 1L;
        String accessToken = authPort.generateAccessToken(memberId);
        String refreshToken = authPort.generateRefreshToken(memberId);

        // when
        Long parseAccessToken = authPort.parseToken(accessToken);
        Long parseRefreshToken = authPort.parseToken(refreshToken);

        // then
        assertAll(
                () -> assertThat(parseAccessToken).isEqualTo(memberId),
                () -> assertThat(parseRefreshToken).isEqualTo(memberId)
        );
    }

    @DisplayName("RefreshToken을 갱신하면 기존에 발급하였던 RefreshToken은 재사용할 수 없다.")
    @Test
    void updateRefreshToken() {
        // given
        Long memberId = 1L;
        String refreshToken = authPort.generateRefreshToken(memberId);

        // when
        String newRefreshToken = authPort.updateRefreshToken(memberId, refreshToken);

        // then
        MemberAuth memberAuth = memberAuthRepository.findByMemberIdAndRefreshToken(memberId, refreshToken).get();
        assertAll(
                () -> assertThat(jwtProvider.parseToken(newRefreshToken)).isEqualTo(memberId),
                () -> assertThat(memberAuth.getRefreshToken()).isEqualTo(newRefreshToken)
        );
    }
}