package me.jincrates.api.ecommerce.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import me.jincrates.api.ecommerce.IntegrationTestSupport;
import me.jincrates.api.global.common.enumtype.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

class MemberTest extends IntegrationTestSupport {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("신규 회원을 등록했을 때, 비밀번호는 권한은 USER이고 상태는 ACTIVE이며 비밀번호는 암호화 처리된다.")
    void saveMember() {
        // given // when
        Member member = Member.create("회원명", "user@email.com", "password", passwordEncoder);

        // then
        assertThat(member).isNotNull()
            .extracting("name", "email", "role", "status")
            .contains("회원명", "user@email.com", Role.USER, Status.ACTIVE);
        assertThat(passwordEncoder.matches("password", member.getPassword())).isTrue();
    }
}