package me.jincrates.claimservice.domain.delivery;

import lombok.Getter;

@Getter
public enum DeliveryTypeCode {
    E("심쿵배송"),
    M("새벽배송"),
    L("펫프택배"),
    V("업체직배송");

    private final String description;

    DeliveryTypeCode(String description) {
        this.description = description;
    }
}
