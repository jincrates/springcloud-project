package me.jincrates.global.common.auth.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberAuthTest {

    @Test
    void setRefreshToken() {
        String expected = "modify_refreshToken";

        MemberAuth memberAuth = MemberAuth.create(1L, "refreshToken");
        memberAuth.setRefreshToken(expected);

        assertThat(memberAuth.getRefreshToken()).isEqualTo(expected);
    }
}