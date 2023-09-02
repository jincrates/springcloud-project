package me.jincrates.api.ecommerce.domain;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("활성화"),
    INACTIVE("비활성화"),
    ;

    private final String description;

    Status(String description) {
        this.description = description;
    }
}
