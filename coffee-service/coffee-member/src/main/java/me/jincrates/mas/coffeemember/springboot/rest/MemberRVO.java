package me.jincrates.mas.coffeemember.springboot.rest;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberRVO {

    private String id;
    private String memberName; // 회원명

    @Builder
    public MemberRVO(String id, String memberName) {
        this.id = id;
        this.memberName = memberName;
    }
}
