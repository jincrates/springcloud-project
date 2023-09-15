package me.jincrates.ecommerce.member.application.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.application.port.MemberUseCase;
import me.jincrates.ecommerce.member.application.service.request.MemberCreateServiceRequest;
import me.jincrates.ecommerce.member.application.service.response.MemberCreateServiceResponse;
import me.jincrates.ecommerce.member.application.service.response.MemberServiceResponse;
import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements MemberUseCase {

    private final PasswordEncoder passwordEncoder;
    private final MemberPort memberPort;

    @Override
    @Transactional
    public MemberCreateServiceResponse register(MemberCreateServiceRequest request) {
        Member member = Member.create(request.getName(), request.getEmail(),
            encryptPassword(request.getPassword()));
        validateDuplicateMember(member.getEmail());
        return MemberCreateServiceResponse.of(memberPort.saveMember(member));
    }

    @Override
    public List<MemberServiceResponse> getMembers() {
        List<Member> members = memberPort.findAllMember();
        return members.stream()
            .map(MemberServiceResponse::of)
            .collect(toList());
    }

    @Override
    public MemberServiceResponse getMemberById(Long memberId) {
        Member member = memberPort.findMemberById(memberId);
        return MemberServiceResponse.of(member);
    }

    @Override
    public MemberServiceResponse getMemberByEmail(String email) {
        Member member = memberPort.findMemberByEmail(email);
        return MemberServiceResponse.of(member);
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
}
