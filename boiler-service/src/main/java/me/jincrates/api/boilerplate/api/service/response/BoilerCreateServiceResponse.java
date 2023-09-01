package me.jincrates.api.boilerplate.api.service.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.api.controller.response.BoilerCreateResponse;
import me.jincrates.api.boilerplate.domain.entity.Boiler;
import me.jincrates.api.boilerplate.domain.entity.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoilerCreateServiceResponse {

    private Long id;
    private BoilerStatus status;
    private Integer number;

    @Builder(access = AccessLevel.PRIVATE)
    private BoilerCreateServiceResponse(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }

    public static BoilerCreateServiceResponse of(Boiler boiler) {
        return BoilerCreateServiceResponse.builder()
                .id(boiler.getId())
                .status(boiler.getStatus())
                .number(boiler.getNumber())
                .build();
    }

    public BoilerCreateResponse toResponse() {
        return BoilerCreateResponse.of(id, status, number);
    }
}
