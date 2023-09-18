package me.jincrates.ecommerce.member.adapter.persistence;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.ecommerce.member.application.port.MemberPort;
import me.jincrates.ecommerce.member.domain.Member;
import me.jincrates.global.common.enumtype.Status;
import org.springframework.stereotype.Component;

@Slf4j
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
        Optional<Member> member = memberRepository.findById(memberId);

        if (member.isEmpty()) {
            log.warn("회원을 찾을 수 없습니다. memberId={}", memberId);
            throw new EntityNotFoundException("회원을 찾을 수 없습니다.");
        }

        if (!isActive(member.get().getStatus())) {
            log.warn("비활성화된 회원입니다. memberId={}", memberId);
            throw new IllegalArgumentException("비활성화된 회원입니다.");
        }

        return member.get();
    }

    @Override
    public Member findMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isEmpty()) {
            log.warn("회원을 찾을 수 없습니다. email={}", email);
            throw new EntityNotFoundException("회원을 찾을 수 없습니다.");
        }

        if (!isActive(member.get().getStatus())) {
            log.warn("비활성화된 회원입니다. email={}", email);
            throw new IllegalArgumentException("비활성화된 회원입니다.");
        }

        return member.get();
    }

    @Override
    public boolean existsMemberByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public void deleteAllMemberInBatch() {
        memberRepository.deleteAllInBatch();
    }

    @Override
    public void saveAllMember(List<Member> members) {
        memberRepository.saveAll(members);
    }

    private boolean isActive(Status memberStatus) {
        return Status.ACTIVE.equals(memberStatus);
    }
}
