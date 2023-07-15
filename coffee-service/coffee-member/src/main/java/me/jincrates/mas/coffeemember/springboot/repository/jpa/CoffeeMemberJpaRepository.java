package me.jincrates.mas.coffeemember.springboot.repository.jpa;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeMemberJpaRepository extends CrudRepository<MemberJpaEntity, UUID> {

    boolean existsByMemberName(String memberName);

    Optional<MemberJpaEntity> findByMemberName(String memberName);
}
