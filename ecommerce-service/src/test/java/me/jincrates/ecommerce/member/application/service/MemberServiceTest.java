package me.jincrates.ecommerce.member.application.service;

import jakarta.persistence.EntityNotFoundException;
import me.jincrates.IntegrationTestSupport;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.application.port.MemberUseCase;
import me.jincrates.ecommerce.member.application.service.request.MemberCreateRequest;
import me.jincrates.ecommerce.member.application.service.request.MemberLoginRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberResponse;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.ecommerce.member.domain.Role;
import me.jincrates.global.common.enumtype.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MemberServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberUseCase memberUseCase;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberPort memberPort;

    @AfterEach
    void tearDown() {
        memberPort.deleteAllMemberInBatch();
    }

    @Test
    @DisplayName("신규 회원을 등록할 때, 비밀번호는 암호화처리 된다.")
    void createMember() {
        // given
        MemberCreateRequest request = new MemberCreateRequest("임칸트", "user@email.com", "password");

        // when
        MemberResponse response = memberUseCase.register(request);


        // then
        assertThat(response).isNotNull()
                .extracting("name", "email", "role", "status")
                .contains("임칸트", "user@email.com", Role.USER, Status.ACTIVE);

        Member savedMember = memberPort.findMemberById(response.id());
        assertThat(passwordEncoder.matches(request.password(), savedMember.getPassword())).isTrue();
    }

    @Test
    @DisplayName("신규 회원을 등록할 때, 이미 가입한 이메일은 가입할 수 없다.")
    void createMemberWithDuplicateEmail() {
        // given
        MemberCreateRequest member = new MemberCreateRequest("임칸트", "user@email.com", "password");
        memberUseCase.register(member);

        MemberCreateRequest request = new MemberCreateRequest("장루소", "user@email.com", "password");

        // when // then
        assertThatThrownBy(() -> memberUseCase.register(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 가입한 회원입니다.");
    }

    @Test
    @DisplayName("로그인할 때, 이메일과 비밀번호가 일치하는 정보가 있어야 한다.")
    void login() {
        // given
        MemberCreateRequest member = new MemberCreateRequest("임칸트", "user@email.com", "password");
        memberUseCase.register(member);

        MemberLoginRequest request = new MemberLoginRequest("user@email.com", "password");

        // when
        MemberResponse response = memberUseCase.login(request);

        // then
        assertThat(response).isNotNull()
                .extracting("name", "email", "role", "status")
                .contains("임칸트", "user@email.com", Role.USER, Status.ACTIVE);
    }

    @Test
    @DisplayName("로그인할 때, 일치하는 이메일이 없으면 예외가 발생한다.")
    void loginWithNotExistsEmail() {
        // given
        MemberCreateRequest member = new MemberCreateRequest("임칸트", "user@email.com", "password");
        memberUseCase.register(member);

        MemberLoginRequest request = new MemberLoginRequest("other@email.com", "password");

        // when // then
        assertThatThrownBy(() -> memberUseCase.login(request))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("회원을 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("로그인할 때, 일치하는 이메일이 있어도 비밀번호가 일치하지 않으면 예외가 발생한다.")
    void loginWithExistsEmailAndInValidPassword() {
        // given
        MemberCreateRequest member = new MemberCreateRequest("임칸트", "user@email.com", "password");
        memberUseCase.register(member);

        MemberLoginRequest request = new MemberLoginRequest("user@email.com", "other-password");

        // when // then
        assertThatThrownBy(() -> memberUseCase.login(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호가 잘못되었습니다.");
    }

    @Test
    @DisplayName("저장된 모든 회원 정보를 조회합니다.")
    void getMembers() {
        // given
        memberPort.saveAllMember(List.of(
                Member.create("사용자1", "user1@email.com", "password1"),
                Member.create("사용자2", "user2@email.com", "password2"),
                Member.create("사용자3", "user3@email.com", "password3"),
                Member.create("사용자4", "user4@email.com", "password4"),
                Member.create("사용자5", "user5@email.com", "password5")
        ));

        // when
        List<MemberResponse> response = memberUseCase.getMembers(PageRequest.of(0, 5));

        // then
        assertThat(response).hasSize(5)
                .extracting("name", "email", "role", "status")
                .containsExactlyInAnyOrder(
                        tuple("사용자1", "user1@email.com", Role.USER, Status.ACTIVE),
                        tuple("사용자2", "user2@email.com", Role.USER, Status.ACTIVE),
                        tuple("사용자3", "user3@email.com", Role.USER, Status.ACTIVE),
                        tuple("사용자4", "user4@email.com", Role.USER, Status.ACTIVE),
                        tuple("사용자5", "user5@email.com", Role.USER, Status.ACTIVE)
                );
    }

    @Test
    @DisplayName("회원 조회시,회원 ID를 통해 회원 정보를 조회합니다.")
    void getMemberById() {
        // given
        Member member = memberPort.saveMember(Member.create("사용자5", "user5@email.com", "password5"));

        // when
        MemberResponse response = memberUseCase.getMemberById(member.getId());

        // then
        assertThat(response).isNotNull()
                .extracting("name", "email", "role", "status")
                .contains("사용자5", "user5@email.com", Role.USER, Status.ACTIVE);
    }

    @Test
    @DisplayName("회원 조회시, 일치하는 회원 ID가 없으면 예외가 발생합니다.")
    void getMemberByIdWithNotExistsMemberId() {
        // given
        memberPort.saveAllMember(List.of(
                Member.create("사용자1", "user1@email.com", "password1"),
                Member.create("사용자2", "user2@email.com", "password2"),
                Member.create("사용자3", "user3@email.com", "password3"),
                Member.create("사용자4", "user4@email.com", "password4"),
                Member.create("사용자5", "user5@email.com", "password5")
        ));
        Long memberId = 99L;

        // when // then
        assertThatThrownBy(() -> memberUseCase.getMemberById(memberId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("회원을 찾을 수 없습니다.");
    }
}