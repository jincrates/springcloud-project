package me.jincrates.api.boilerplate.domain.entity;

import lombok.Getter;

@Getter
public enum BoilerStatus {
    NOT_STARTED("할 일"),
    IN_PROGRESS("진행 중"),
    COMPLETED("완료");

    private final String description;

    BoilerStatus(String description) {
        this.description = description;
    }
}
