package me.jincrates.api.ecommerce.members.application.port;

import me.jincrates.api.ecommerce.members.domain.Member;

import java.util.List;

public interface MemberPort {
    Member saveMember(Member member);

    List<Member> findAllMember();

    Member findMemberById(Long memberId);

    Member findMemberByEmail(String email);

    boolean existsMemberByEmail(String email);
}
