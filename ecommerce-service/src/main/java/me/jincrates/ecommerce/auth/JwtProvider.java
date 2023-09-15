package me.jincrates.ecommerce.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String subject = "Authentication-Member";  //  토큰 제목
    private final String audience = "Member";  // 토큰 대상자
    private final String issuer = "jincrates";  // 토큰 발급자
    private final String claimName = "memberId";

    public String generateJwtToken(Long memberId) {
        return Jwts.builder()
            .setIssuer(issuer)
            .setSubject(subject)
            .setAudience(audience)
            .setIssuedAt(new Date())
            .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusHours(1)))  // 1시간 만료
            .claim(claimName, memberId)
            .signWith(key)
            .compact();
    }

    public Long parseToken(String jwtToken) {
        return Jwts.parserBuilder()
            .setSigningKey(key).build()
            .parseClaimsJws(jwtToken)
            .getBody().get(claimName, Long.class);
    }
}
