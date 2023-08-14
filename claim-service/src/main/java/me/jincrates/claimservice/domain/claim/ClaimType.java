package me.jincrates.claimservice.domain.claim;

import lombok.Getter;

@Getter
public enum ClaimType {
    RETURN("반품"),
    EXCHANGE("교환");

    private final String description;

    ClaimType(String description) {
        this.description = description;
    }
}
