package me.jincrates.mas.coffeemember.springboot.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeMemberJpaRepository extends JpaRepository<MemberJpaEntity, String> {

    boolean existsByMemberName(String memberName);
}
