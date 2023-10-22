package me.jincrates.global.common.auth.adapter;

import me.jincrates.global.common.auth.domain.MemberAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface MemberAuthRepository extends JpaRepository<MemberAuth, Long> {
    Optional<MemberAuth> findByMemberId(Long memberId);

    Optional<MemberAuth> findByMemberIdAndRefreshToken(Long memberId, String refreshToken);
}
