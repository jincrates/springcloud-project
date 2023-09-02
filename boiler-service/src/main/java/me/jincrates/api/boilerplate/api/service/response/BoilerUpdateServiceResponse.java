package me.jincrates.api.boilerplate.api.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.api.boilerplate.api.controller.response.BoilerUpdateResponse;
import me.jincrates.api.boilerplate.domain.boiler.Boiler;
import me.jincrates.api.boilerplate.domain.boiler.BoilerStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "수정된 Boiler DTO")
public class BoilerUpdateServiceResponse {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "상태")
    private BoilerStatus status;

    @Schema(description = "NUMBER")
    private Integer number;

    @Builder(access = AccessLevel.PRIVATE)
    private BoilerUpdateServiceResponse(Long id, BoilerStatus status, Integer number) {
        this.id = id;
        this.status = status;
        this.number = number;
    }

    public static BoilerUpdateServiceResponse of(Boiler boiler) {
        return BoilerUpdateServiceResponse.builder()
                .id(boiler.getId())
                .status(boiler.getStatus())
                .number(boiler.getNumber())
                .build();
    }

    public BoilerUpdateResponse toResponse() {
        return BoilerUpdateResponse.of(id, status, number);
    }
}
