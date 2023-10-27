package me.jincrates.ecommerce.store.domain;

import lombok.Getter;

@Getter
public enum StoreStatus {
    READY("준비중"),
    OPEN("오픈"),
    CLOSE("마감");

    private final String description;

    StoreStatus(String description) {
        this.description = description;
    }
}
