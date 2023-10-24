package me.jincrates.ecommerce.member.domain;

import lombok.Getter;

@Getter
public enum Role {
    USER("사용자"),
    MANAGER("매니저"),
    ADMIN("관리자"),
    ;

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
