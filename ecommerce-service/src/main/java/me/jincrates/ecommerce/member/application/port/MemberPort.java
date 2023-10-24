package me.jincrates.ecommerce.member.application.port;

import me.jincrates.ecommerce.member.domain.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberPort {

    Member saveMember(Member member);

    List<Member> findAllMember(Pageable pageable);

    Member findMemberById(Long memberId);

    Member findMemberByEmail(String email);

    boolean existsMemberByEmail(String email);

    void deleteAllMemberInBatch();

    void saveAllMember(List<Member> members);
}
