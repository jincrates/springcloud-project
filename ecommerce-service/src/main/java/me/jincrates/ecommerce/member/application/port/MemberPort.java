package me.jincrates.ecommerce.member.application.port;

import me.jincrates.ecommerce.member.domain.Member;

import java.util.List;

public interface MemberPort {

    Member saveMember(Member member);

    List<Member> findAllMember();

    Member findMemberById(Long memberId);

    Member findMemberByEmail(String email);

    boolean existsMemberByEmail(String email);

    void deleteAllMemberInBatch();

    void saveAllMember(List<Member> members);
}
