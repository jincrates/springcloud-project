package me.jincrates.mas.coffeemember.springboot.rest;

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

    @GetMapping("/coffee-member/v1.0/{memberName}")
    public boolean existsByMemberName(@PathVariable("memberName") String memberName) {
        return coffeeMemberJpaRepository.existsByMemberName(memberName);
    }

    @PostMapping("/coffee-member/v1.0")
    public MemberRVO saveMember(@RequestBody MemberRVO memberRVO) {
        MemberJpaEntity memberJpaEntity = MemberJpaEntity.builder()
            .memberName(memberRVO.getMemberName())
            .build();

        MemberJpaEntity member = coffeeMemberJpaRepository.save(memberJpaEntity);

        return MemberRVO.builder()
            .id(member.getId())
            .memberName(member.getMemberName())
            .build();
    }
}
