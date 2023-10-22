package me.jincrates.global.common.auth.adapter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Component
class JwtProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String subject = "Authentication-Member";  //  토큰 제목
    private final String audience = "Member";  // 토큰 대상자
    private final String issuer = "jincrates";  // 토큰 발급자
    private final String claimName = "memberId";
    private final Timestamp accessTokenExpiration = Timestamp.valueOf(LocalDateTime.now().plusSeconds(1)); // 1시간 만료
    private final Timestamp refreshTokenExpiration = Timestamp.valueOf(LocalDateTime.now().plusDays(7)); // 7일 만료

    public String generateAccessToken(Long memberId) {
        return generateToken(memberId, accessTokenExpiration);
    }

    public String generateRefreshToken(Long memberId) {
        return generateToken(memberId, refreshTokenExpiration);
    }

    public Long parseToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .get(claimName, Long.class);
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String generateToken(Long memberId, Timestamp tokenExpiration) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(tokenExpiration)
                .claim(claimName, memberId)
                .signWith(key)
                .compact();
    }
}
