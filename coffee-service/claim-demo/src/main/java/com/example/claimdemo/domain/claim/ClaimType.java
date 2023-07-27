package com.example.claimdemo.domain.claim;

import lombok.Getter;

@Getter
public enum ClaimType {
    RETURN("반품"),
    EXCHANGE("교환");

    private String description;

    ClaimType(String description) {
        this.description = description;
    }
}
