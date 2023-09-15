package me.jincrates.api.ecommerce.members.adapter.persistence;

import java.util.Optional;
import me.jincrates.api.ecommerce.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
