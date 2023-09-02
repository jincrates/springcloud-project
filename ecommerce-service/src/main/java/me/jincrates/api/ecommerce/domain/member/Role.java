package me.jincrates.api.ecommerce.domain.member;

import lombok.Getter;

@Getter
public enum Role {
    USER("사용자"),
    AMDIN("관리자"),
    ;

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
