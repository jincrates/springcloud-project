package me.jincrates.api.claims.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class Recipient {
    private final Long userId;
    private final String name;
    private final String mobileNo;

    @Builder
    private Recipient(Long userId, String name, String mobileNo) {
        this.userId = userId;
        this.name = name;
        this.mobileNo = mobileNo;
    }
}
