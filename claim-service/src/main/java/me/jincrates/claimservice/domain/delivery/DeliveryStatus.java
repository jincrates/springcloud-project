package me.jincrates.claimservice.domain.delivery;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    REQUESTED("접수"),
    CANCELED("취소"),
    PROGRESS("진행중"),
    COMPLETED("완료");


    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }
}
