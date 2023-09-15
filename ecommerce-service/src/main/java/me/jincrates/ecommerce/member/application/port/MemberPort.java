package me.jincrates.ecommerce.member.application.port;

import java.util.List;
import me.jincrates.ecommerce.member.domain.Member;

public interface MemberPort {

    Member saveMember(Member member);

    List<Member> findAllMember();

    Member findMemberById(Long memberId);

    Member findMemberByEmail(String email);

    boolean existsMemberByEmail(String email);
}
