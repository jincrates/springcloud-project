package me.jincrates.ecommerce.member.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MemberAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다. id=" + memberId));
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다. email=" + email));
    }

    @Override
    public boolean existsMemberByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
