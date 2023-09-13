package me.jincrates.api.ecommerce.members.api.service;

import me.jincrates.api.ecommerce.IntegrationTestSupport;
import me.jincrates.api.ecommerce.members.api.service.request.MemberCreateServiceRequest;
import me.jincrates.api.ecommerce.members.api.service.response.MemberCreateServiceResponse;
import me.jincrates.api.ecommerce.members.domain.MemberRepository;
import me.jincrates.api.ecommerce.members.domain.Role;
import me.jincrates.api.global.common.enumtype.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("신규 회원을 등록합니다.")
    void createMember() {
        // given
        MemberCreateServiceRequest request = MemberCreateServiceRequest.create("회원명", "user@email.com",
                "password");

        // when
        MemberCreateServiceResponse result = memberService.register(request);

        // then
        assertThat(result).isNotNull()
                .extracting("name", "email", "role", "status")
                .contains("회원명", "user@email.com", Role.USER, Status.ACTIVE);
    }

    @Test
    @DisplayName("신규 회원을 등록할 때, 이미 가입한 이메일은 가입할 수 없습니다.")
    void createMemberWithDuplicateEmail() {
        // given
        MemberCreateServiceRequest member = MemberCreateServiceRequest.create("회원명2", "user@email.com",
                "password1");
        memberService.register(member);

        MemberCreateServiceRequest request = MemberCreateServiceRequest.create("회원명2",
                "user@email.com", "password2");

        // when // then
        assertThatThrownBy(() -> memberService.register(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 가입한 회원입니다.");
    }
}