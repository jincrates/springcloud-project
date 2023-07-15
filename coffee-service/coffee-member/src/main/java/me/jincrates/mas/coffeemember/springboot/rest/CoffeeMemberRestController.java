package me.jincrates.mas.coffeemember.springboot.rest;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import me.jincrates.mas.coffeemember.springboot.repository.jpa.CoffeeMemberJpaRepository;
import me.jincrates.mas.coffeemember.springboot.repository.jpa.MemberJpaEntity;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequiredArgsConstructor
public class CoffeeMemberRestController {

    private final CoffeeMemberJpaRepository coffeeMemberJpaRepository;

    @GetMapping("/member/{memberName}")
    public boolean existsByMemberName(@PathVariable("memberName") String memberName) {
//        return coffeeMemberJpaRepository.existsByMemberName(memberName);

        Optional<MemberJpaEntity> memberOptional = coffeeMemberJpaRepository.findByMemberName(
            memberName);

        return memberOptional.isPresent();
    }

    @PostMapping("/member")
    public MemberRVO saveMember(@RequestBody MemberRVO memberRVO) {
        MemberJpaEntity memberJpaEntity = MemberJpaEntity.builder()
            .id(UUID.randomUUID())
            .memberName(memberRVO.getMemberName())
            .build();

        MemberJpaEntity member = coffeeMemberJpaRepository.save(memberJpaEntity);

        return MemberRVO.builder()
            .id(member.getId().toString())
            .memberName(member.getMemberName())
            .build();
    }
}
