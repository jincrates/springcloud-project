package me.jincrates.orderservice.domain.claim;

import lombok.Getter;

@Getter
public enum ClaimType {
    RETURN("반품"),
    EXCHANGE("교환");

    private final String description;

    ClaimType(String description) {
        this.description = description;
    }

    public boolean isExchange() {
        return this == EXCHANGE;
    }

    public boolean isReturn() {
        return this == RETURN;
    }
}
