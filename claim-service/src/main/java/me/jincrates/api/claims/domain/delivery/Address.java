package me.jincrates.api.claims.domain.delivery;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class Address {
    private final String zipCode;
    private final String address1;
    private final String address2;
    private final String roadAddress;
    private final String landAddress;

    @Builder
    private Address(String zipCode, String address1, String address2, String roadAddress, String landAddress) {
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
        this.roadAddress = roadAddress;
        this.landAddress = landAddress;
    }
}
