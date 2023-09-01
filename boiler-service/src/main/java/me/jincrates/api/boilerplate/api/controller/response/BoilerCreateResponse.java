package me.jincrates.api.boilerplate.api.controller.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.jincrates.api.boilerplate.domain.entity.Boiler;
import me.jincrates.api.boilerplate.domain.entity.BoilerStatus;

@Getter
public class BoilerCreateResponse {

    private Long id;
    private BoilerStatus status;
    private Integer number;

    @Builder(access = AccessLevel.PRIVATE)
    private BoilerCreateResponse(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }

    public static BoilerCreateResponse of(Boiler boiler) {
        return BoilerCreateResponse.builder()
            .id(boiler.getId())
            .status(boiler.getStatus())
            .number(boiler.getNumber())
            .build();
    }
}
