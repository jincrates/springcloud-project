package me.jincrates.global.common.auth.adapter;

import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.global.common.auth.application.AuthPort;
import me.jincrates.global.common.auth.domain.MemberAuth;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
class AuthAdapter implements AuthPort {
    private final JwtProvider jwtProvider;
    private final MemberAuthRepository memberAuthRepository;

    @Override
    public String generateAccessToken(Long memberId) {
        return jwtProvider.generateAccessToken(memberId);
    }

    @Override
    @Transactional
    public String generateRefreshToken(Long memberId) {
        MemberAuth memberAuth = memberAuthRepository.findByMemberId(memberId)
                .orElse(MemberAuth.create(memberId, null));

        String refreshToken = memberAuth.getRefreshToken();
        if (refreshToken == null || !jwtProvider.validateToken(refreshToken)) {
            refreshToken = jwtProvider.generateRefreshToken(memberId);
            saveMemberAuth(memberAuth, refreshToken);
        }

        return refreshToken;
    }

    @Override
    public Long parseToken(String jwtToken) {
        return jwtProvider.parseToken(jwtToken);
    }

    @Override
    @Transactional
    public String updateRefreshToken(Long memberId, final String refreshToken) {
        MemberAuth memberAuth = memberAuthRepository.findByMemberIdAndRefreshToken(memberId, refreshToken)
                .orElseThrow(() -> {
                    log.warn("회원인증 토큰이 존재하지 않습니다. memberId={}, refreshToken={}", memberId, refreshToken);
                    return new EntityNotFoundException("회원인증 토큰이 존재하지 않습니다.");
                });

        if (!jwtProvider.validateToken(memberAuth.getRefreshToken())) {
            log.warn("유효하지 않은 토큰입니다. refreshToken={}", refreshToken);
            throw new JwtException("유효하지 않은 토큰입니다.");
        }

        // 유효한 토큰인 경우 refresh token 새로 갱신
        String newRefreshToken = jwtProvider.generateRefreshToken(memberId);
        saveMemberAuth(memberAuth, newRefreshToken);

        return newRefreshToken;
    }

    private void saveMemberAuth(MemberAuth memberAuth, String refreshToken) {
        memberAuth.setRefreshToken(refreshToken);
        memberAuthRepository.save(memberAuth);
    }
}
