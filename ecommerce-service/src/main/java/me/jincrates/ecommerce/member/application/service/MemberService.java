package me.jincrates.ecommerce.member.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.application.port.MemberUseCase;
import me.jincrates.ecommerce.member.application.service.request.MemberCreateRequest;
import me.jincrates.ecommerce.member.application.service.request.MemberLoginRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberResponse;
import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements MemberUseCase {

    private final PasswordEncoder passwordEncoder;
    private final MemberPort memberPort;

    @Override
    @Transactional
    public MemberResponse register(MemberCreateRequest request) {
        Member member = Member.create(request.name(), request.email(), encryptPassword(request.password()));
        validateDuplicateMember(member.getEmail());
        return MemberResponse.of(memberPort.saveMember(member));
    }

    @Override
    public MemberResponse login(MemberLoginRequest request) {
        Member member = memberPort.findMemberByEmail(request.email());
        if (!isValidPassword(request.password(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }
        return MemberResponse.of(member);
    }

    @Override
    public List<MemberResponse> getMembers() {
        List<Member> members = memberPort.findAllMember();
        return members.stream()
                .map(MemberResponse::of)
                .collect(toList());
    }

    @Override
    public MemberResponse getMemberById(Long memberId) {
        Member member = memberPort.findMemberById(memberId);
        return MemberResponse.of(member);
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void validateDuplicateMember(String email) {
        if (memberPort.existsMemberByEmail(email)) {
            log.warn("이미 가입한 회원입니다. email={}", email);
            throw new IllegalArgumentException("이미 가입한 회원입니다.");
        }
    }

    private boolean isValidPassword(String rowPassword, String encodedPassword) {
        return passwordEncoder.matches(rowPassword, encodedPassword);
    }
}
