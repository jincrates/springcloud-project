package me.jincrates.ecommerce.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JwtProviderTest {

    JwtProvider jwtProvider = new JwtProvider();

    @Test
    void generateJwtToken() {
        // given
        Long memberId1 = 1L;
        Long memberId2 = 2L;

        // when
        String jwtToken1 = jwtProvider.generateJwtToken(memberId1);
        String jwtToken2 = jwtProvider.generateJwtToken(memberId2);

        // then
        assertThat(jwtProvider.parseToken(jwtToken1)).isEqualTo(memberId1);
        assertThat(jwtProvider.parseToken(jwtToken2)).isEqualTo(memberId2);
    }
}