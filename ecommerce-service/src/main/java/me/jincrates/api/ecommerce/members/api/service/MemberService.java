package me.jincrates.api.ecommerce.members.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.api.ecommerce.members.api.service.request.MemberCreateServiceRequest;
import me.jincrates.api.ecommerce.members.api.service.response.MemberCreateServiceResponse;
import me.jincrates.api.ecommerce.members.api.service.response.MemberServiceResponse;
import me.jincrates.api.ecommerce.members.domain.Member;
import me.jincrates.api.ecommerce.members.domain.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreateServiceResponse register(MemberCreateServiceRequest request) {
        Member member = Member.create(request.getName(), request.getEmail(), request.getPassword(), passwordEncoder);
        validateDuplicateMember(member.getEmail());
        return MemberCreateServiceResponse.of(memberRepository.save(member));
    }

    public MemberServiceResponse getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다. id=" + memberId));
        return MemberServiceResponse.of(member);
    }

    public List<MemberServiceResponse> getMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberServiceResponse::of)
                .collect(toList());
    }

    public MemberServiceResponse getMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다. email=" + email));
        return MemberServiceResponse.of(member);
    }

    private void validateDuplicateMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isPresent()) {
            log.warn("이미 가입된 회원입니다. email={}", email);
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }
}
