package me.jincrates.api.boilerplate.api.service.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.api.controller.response.BoilerReadResponse;
import me.jincrates.api.boilerplate.domain.boiler.Boiler;
import me.jincrates.api.boilerplate.domain.boiler.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoilerReadServiceResponse {

    private Long id;
    private BoilerStatus status;
    private Integer number;

    @Builder(access = AccessLevel.PRIVATE)
    private BoilerReadServiceResponse(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }

    public static BoilerReadServiceResponse of(Boiler boiler) {
        return BoilerReadServiceResponse.builder()
                .id(boiler.getId())
                .status(boiler.getStatus())
                .number(boiler.getNumber())
                .build();
    }

    public BoilerReadResponse toResponse() {
        return BoilerReadResponse.of(id, status, number);
    }
}
