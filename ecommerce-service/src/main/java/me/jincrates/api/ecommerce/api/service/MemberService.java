package me.jincrates.api.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.api.ecommerce.api.service.request.MemberCreateServiceRequest;
import me.jincrates.api.ecommerce.api.service.response.MemberCreateServiceResponse;
import me.jincrates.api.ecommerce.domain.member.Member;
import me.jincrates.api.ecommerce.domain.member.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreateServiceResponse saveMember(MemberCreateServiceRequest request) {
        Member member = Member.create(request.getName(), request.getEmail(), request.getPassword(), passwordEncoder);
        validateDuplicateMember(member);
        return MemberCreateServiceResponse.of(memberRepository.save(member));
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()) {
            log.warn("이미 가입된 회원입니다. email={}", member.getEmail());
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }
}
