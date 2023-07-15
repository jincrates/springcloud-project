package me.jincrates.msa.coffeeorder.springboot.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("COFFEE-MEMBER")
public interface MsaServiceCoffeeMember {

    @GetMapping("/member/{memberName}")
    boolean existsByMemberName(@PathVariable("memberName") String memberName);

}
