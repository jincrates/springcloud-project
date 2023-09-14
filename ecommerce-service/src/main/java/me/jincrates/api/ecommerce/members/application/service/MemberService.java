package me.jincrates.api.ecommerce.members.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.api.ecommerce.members.application.port.MemberPort;
import me.jincrates.api.ecommerce.members.application.service.request.MemberCreateServiceRequest;
import me.jincrates.api.ecommerce.members.application.service.response.MemberCreateServiceResponse;
import me.jincrates.api.ecommerce.members.application.service.response.MemberServiceResponse;
import me.jincrates.api.ecommerce.members.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberPort memberPort;

    @Transactional
    public MemberCreateServiceResponse register(MemberCreateServiceRequest request) {
        Member member = Member.create(request.getName(), request.getEmail(), encryptPassword(request.getPassword()));
        validateDuplicateMember(member.getEmail());
        return MemberCreateServiceResponse.of(memberPort.saveMember(member));
    }

    public List<MemberServiceResponse> getMembers() {
        List<Member> members = memberPort.findAllMember();
        return members.stream()
                .map(MemberServiceResponse::of)
                .collect(toList());
    }

    public MemberServiceResponse getMemberById(Long memberId) {
        Member member = memberPort.findMemberById(memberId);
        return MemberServiceResponse.of(member);
    }

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
